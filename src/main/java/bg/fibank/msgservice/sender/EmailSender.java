package bg.fibank.msgservice.sender;

import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements MessageSender {

    private final LoggerUtil logger;

    public EmailSender(LoggerUtil logger) {
        this.logger = logger;
    }

    @Override
    public void sendMessage(String body) {
        logger.info(this.getClass(), "Sending Email with body: {}", body);
        // Add actual email sending logic here
    }
}
