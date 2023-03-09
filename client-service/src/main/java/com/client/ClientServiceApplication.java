package com.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.client.controller","com.client.exception" ,"com.client.service","com.commons.clients.models.entity", "com.client.feignclient"})
@EntityScan("com.commons.clients.models.entity")
@EnableJpaRepositories("com.client.repository")
@EnableFeignClients("com.client.feignclient")
@EnableEurekaClient
public class ClientServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
