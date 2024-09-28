package com.example.YoungJu_Lee_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YoungJuLeeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoungJuLeeSpringApplication.class, args);
	}

}
