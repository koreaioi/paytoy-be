package com.PayToy_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PayToyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayToyBeApplication.class, args);
	}

}
