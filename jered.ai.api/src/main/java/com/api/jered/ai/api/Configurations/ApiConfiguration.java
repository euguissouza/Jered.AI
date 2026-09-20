package com.api.jered.ai.api.Configurations;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GeminiFiles;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {



    @Bean
    public GeminiFiles modelo(){
        String apiKey = System.getenv("GEMINI_API_KEY");
        return GeminiFiles.builder().apiKey(apiKey).build();
    }


}
