package com.currencyexchangerate.exchangeservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ExchangeService2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeService2Application.class, args);
	}

}
