package com.api.jered.ai.api;

import com.api.jered.ai.api.Service.AiEngine;
import org.springframework.ai.model.google.genai.autoconfigure.chat.GoogleGenAiChatAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { GoogleGenAiChatAutoConfiguration.class })public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		System.out.println("Jered está ATIVO!!!");
		System.out.println("====================================================");
		System.out.println("JERED.AI - V:0.0.2 - ALPLHATEST - CHAT TERMINAL");
		System.out.println("====================================================");

		AiEngine engine = context.getBean(AiEngine.class);
		engine.ChatTerminal();
	}

}
