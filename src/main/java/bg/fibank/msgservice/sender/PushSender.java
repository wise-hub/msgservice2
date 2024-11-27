package bg.fibank.msgservice.sender;

import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.stereotype.Component;

@Component
public class PushSender implements MessageSender {

    private final LoggerUtil logger;

    public PushSender(LoggerUtil logger) {
        this.logger = logger;
    }

    @Override
    public void sendMessage(String body) {
        logger.info(this.getClass(), "Sending Push Notification with body: {}", body);
        // Add actual Push Notification logic here
    }
}
