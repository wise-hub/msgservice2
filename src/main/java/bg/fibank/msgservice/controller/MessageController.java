package bg.fibank.msgservice.controller;

import bg.fibank.msgservice.service.MessageProcessingService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/send")
public class MessageController {

    private final MessageProcessingService service;

    @Autowired
    public MessageController(MessageProcessingService service) {
        this.service = service;
    }

    /**
     * Processes messages for a specific type.
     *
     * @param type          The type of the message (email, sms, push, viber).
     * @param apiKey        The API key for authorization.
     * @param messageBodies The list of message contents.
     * @return A response indicating the result.
     */
    @PostMapping("/{type}")
    public String sendMessages(
            @PathVariable
            @Pattern(regexp = "email|sms|push|viber", flags = Pattern.Flag.CASE_INSENSITIVE,
                    message = "Invalid message type. Allowed values are: email, sms, push, viber.") String type,
            @RequestHeader("X-MSGSERVICE-AUTH") String apiKey,
            @RequestBody List<String> messageBodies) {

        // Delegate processing to the service
        service.processMessages(type.toUpperCase(), messageBodies);

        return "Processing started for type: " + type.toUpperCase();
    }
}
