import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

package com.example.delivery.config;


@Configuration
public class AppConfig {
    
    // Додаткові Spring біни або конфігурації можна додати тут

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}