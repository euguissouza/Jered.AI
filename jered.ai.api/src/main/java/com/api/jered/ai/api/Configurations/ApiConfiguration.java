package com.api.jered.ai.api.Configurations;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GeminiFiles;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
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

    // Configuração de sistema de chat(não mandar para produção!)
    @Bean
    public ChatModel geminiChat(){
        return GoogleAiGeminiChatModel
                .builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.5-flash")
                .responseFormat(ResponseFormat.TEXT)
                .build();
    }

}
