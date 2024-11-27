package bg.fibank.msgservice.service;

import bg.fibank.msgservice.model.CustomerMessage;
import bg.fibank.msgservice.repository.MessageRepository;
import bg.fibank.msgservice.sender.MessageSenderFactory;
import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;

@Service
public class ScheduledMessageService {

    private final MessageRepository repository;
    private final MessageSenderFactory senderFactory;
    private final LoggerUtil logger;

    public ScheduledMessageService(MessageRepository repository, MessageSenderFactory senderFactory, LoggerUtil logger) {
        this.repository = repository;
        this.senderFactory = senderFactory;
        this.logger = logger;
    }

    /**
     * Scheduled task that fetches and sends messages every second.
     */
    @Scheduled(fixedDelay = 1000) // Runs every second
    public void fetchAndSendMessages() {
        logger.info(this.getClass(), "Starting fetch-and-send cycle.");

        List<CustomerMessage> messages = repository.fetchPendingMessages();
        logger.info(this.getClass(), "Fetched {} messages to process.", messages.size());

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Submit each message for processing in its own virtual thread
            for (CustomerMessage message : messages) {
                executor.submit(() -> processMessage(message));
            }
        } catch (Exception e) {
            logger.error(this.getClass(), "Error during fetch-and-send cycle.", e);
        }

        logger.info(this.getClass(), "Completed fetch-and-send cycle.");
    }

    /**
     * Processes a single message in its own virtual thread.
     *
     * @param message The message to process.
     */
    private void processMessage(CustomerMessage message) {
        try {
            // Lock the message as "in progress"
            repository.markAsWaiting(message.dbId());

            // Retrieve the appropriate sender and send the message
            var sender = senderFactory.getSender(message.channel());
            if (sender == null) {
                throw new IllegalArgumentException("Unsupported channel: " + message.channel());
            }

            sender.sendMessage(message.fullText());

            // Mark the message as sent
            repository.markAsSent(message.dbId());
            logger.info(this.getClass(), "Message ID {} sent successfully.", message.dbId());
        } catch (Exception e) {
            // Mark the message as errored and log the exception
            repository.markAsError(message.dbId());
            logger.error(this.getClass(), "Failed to process message ID {}.", message.dbId(), e);
        }
    }
}
