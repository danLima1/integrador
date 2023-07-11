package com.projeto.integrador;

import com.projeto.integrador.Service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication

public class IntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorApplication.class, args);
	}

}
