package com.insurance.claimservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.insurance.claimservice.dto.CustomerDTO;

@FeignClient(name = "CUSTOMERSERVICE",path="/customers")
public interface CustomerClient {
	@GetMapping("/getCustomerById/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);
}
