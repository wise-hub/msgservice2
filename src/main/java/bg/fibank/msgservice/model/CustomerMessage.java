package bg.fibank.msgservice.model;

public record CustomerMessage(
        Long dbId,
        String phone,
        String channel,
        String fullText
) {}
