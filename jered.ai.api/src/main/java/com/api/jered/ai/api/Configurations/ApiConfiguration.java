package com.api.jered.ai.api.Configurations;

import dev.langchain4j.model.googleai.GeminiFiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {



    @Bean
    public GeminiFiles filesApi(){
        String apiKey = System.getenv("GEMINI_API_KEY");
        return GeminiFiles.builder()
                .apiKey(apiKey)
                .build();
    }

}
