package bg.fibank.msgservice.service;

import bg.fibank.msgservice.sender.MessageSenderFactory;
import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;

@Service
public class MessageProcessingService {

    private final LoggerUtil logger;
    private final MessageSenderFactory senderFactory;

    public MessageProcessingService(LoggerUtil logger, MessageSenderFactory senderFactory) {
        this.logger = logger;
        this.senderFactory = senderFactory;
    }

    public void processMessages(String type, List<String> messageBodies) {
        logger.info(this.getClass(), "Processing {} messages for type: {}", messageBodies.size(), type);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            messageBodies.forEach(body -> executor.submit(() -> {
                try {
                    var sender = senderFactory.getSender(type);
                    if (sender == null) {
                        throw new IllegalArgumentException("Unsupported message type: " + type);
                    }
                    sender.sendMessage(body);
                    logger.info(this.getClass(), "Message processed successfully: {}", body);
                } catch (Exception e) {
                    logger.error(this.getClass(), "Failed to process message: {}", body, e);
                }
            }));
        }
    }
}
