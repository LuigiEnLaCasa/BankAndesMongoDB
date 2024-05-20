package com.uniandes.bancandes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BancAndesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancAndesApplication.class, args);
	}

}
