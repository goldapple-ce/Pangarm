package site.pangarm.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Value("${spring.precedent-client.url}")
    private String BASE_URL;

    @Bean
    public WebClient precedentClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
