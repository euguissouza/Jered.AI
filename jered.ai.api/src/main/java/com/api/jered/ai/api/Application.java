package com.api.jered.ai.api;

import com.api.jered.ai.api.Service.AiEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
	public class Application {

		public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		System.out.println("Jered está ATIVO!!!");
		System.out.println("=================================");
		System.out.println("JERED.AI - V:1.0.0 - AlphaTest");
		System.out.println("=================================");

		AiEngine engine = context.getBean(AiEngine.class);

		}

}
