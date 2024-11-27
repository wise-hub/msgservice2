package bg.fibank.msgservice.sender;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageSenderFactory {

    private final Map<String, MessageSender> senderMap;

    public MessageSenderFactory(EmailSender emailSender, SmsSender smsSender, PushSender pushSender, ViberSender viberSender) {
        senderMap = Map.of(
                "EMAIL", emailSender,
                "SMS", smsSender,
                "PUSH", pushSender,
                "VIBER", viberSender
        );
    }

    public MessageSender getSender(String type) {
        return senderMap.get(type.toUpperCase());
    }
}
