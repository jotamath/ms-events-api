package io.github.jotamath.eventmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsEventsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEventsApiApplication.class, args);
	}

}
