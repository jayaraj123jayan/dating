package com.suu.service.matchingengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MatchingEngineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchingEngineServiceApplication.class, args);
	}

}
