package com.insurance.policyservice.feignClient;

import com.insurance.policyservice.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMERSERVICE",path="/customers")
public interface CustomerClient {
	@GetMapping("/getCustomerById/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);
}
