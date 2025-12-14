package io.github.jotamath.eventmicroservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.jotamath.eventmicroservice.dtos.EmailRequestDTO;

@FeignClient(name="email-service", url="http://endereco-do-servico-de-email")
public interface EmailServiceClient {
	
	@PostMapping("/send")
	void sendEmail(@RequestBody EmailRequestDTO emailRequest);

}
