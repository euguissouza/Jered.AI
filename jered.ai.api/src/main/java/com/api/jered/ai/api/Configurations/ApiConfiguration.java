package com.api.jered.ai.api.Configurations;


import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {


    @Bean
    public Client clientAI() {
        return Client
                .builder()
                .apiKey("GEMINI_API_KEY")
                .build();
    }



}