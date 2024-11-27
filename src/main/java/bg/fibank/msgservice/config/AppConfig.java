package bg.fibank.msgservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${msgservice.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
