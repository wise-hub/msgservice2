package bg.fibank.msgservice.sender;

import bg.fibank.msgservice.util.LoggerUtil;
import org.springframework.stereotype.Component;

@Component
public class ViberSender implements MessageSender {

    private final LoggerUtil logger;

    public ViberSender(LoggerUtil logger) {
        this.logger = logger;
    }

    @Override
    public void sendMessage(String body) {
        logger.info(this.getClass(), "Sending Viber Message with body: {}", body);
        // Add actual Viber message logic here
    }
}
