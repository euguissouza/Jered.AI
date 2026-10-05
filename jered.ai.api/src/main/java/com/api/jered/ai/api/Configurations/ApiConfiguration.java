package com.api.jered.ai.api.Configurations;


import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.DocumentContent;
import com.google.genai.types.File;
import com.google.genai.types.UploadFileConfig;
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