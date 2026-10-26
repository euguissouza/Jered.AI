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
                .apiKey("AQ.Ab8RN6JgFd1beae7_eyENuqzawa169uje-O3ySndIq7c0PzlGw")
                .build();
    }



}