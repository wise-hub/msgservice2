package bg.fibank.msgservice.sender;

import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender {

    private final LoggerUtil logger;

    public SmsSender(LoggerUtil logger) {
        this.logger = logger;
    }

    @Override
    public void sendMessage(String body) {
        logger.info(this.getClass(), "Sending SMS with body: {}", body);
        // Add actual SMS sending logic here
    }
}
