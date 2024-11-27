package bg.fibank.msgservice.util;

import bg.fibank.msgservice.model.CustomerMessage;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final LoggerUtil logger;

    public MessageSender(LoggerUtil logger) {
        this.logger = logger;
    }

    public String sendMessage(CustomerMessage msg) throws Exception {
        logger.debug(this.getClass(), "Sending message ID: " + msg.dbId() + " via channel: " + msg.channel());

        switch (msg.channel().toUpperCase()) {
            case "VIBER":
            case "SMS":
                return sendSMSOrViber(msg);
            case "EMAIL":
                return sendEmail(msg);
            case "PUSH":
                return sendPush(msg);
            default:
                logger.error(this.getClass(), "Unsupported channel for message ID: " + msg.dbId());
                throw new IllegalArgumentException("Unsupported channel");
        }
    }

    private String sendSMSOrViber(CustomerMessage msg) {
        logger.info(this.getClass(), "Sending SMS/Viber to: " + msg.phone());
        return "Message sent via SMS/Viber";
    }

    private String sendEmail(CustomerMessage msg) {
        logger.info(this.getClass(), "Sending Email to: " + msg.phone());
        return "Message sent via Email";
    }

    private String sendPush(CustomerMessage msg) {
        logger.info(this.getClass(), "Sending Push notification to: " + msg.phone());
        return "Message sent via Push notification";
    }
}
