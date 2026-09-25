package com.api.jered.ai.api;

import org.springframework.ai.model.google.genai.autoconfigure.chat.GoogleGenAiChatAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { GoogleGenAiChatAutoConfiguration.class })public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Jered está ATIVO!!!");
		System.out.println("==================================");
		System.out.println("JERED.AI - V:0.0.1 - ALPLHATEST");
		System.out.println("==================================");

	}

}
