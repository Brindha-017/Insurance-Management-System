package com.insurance.claimservice.feignClient;

import com.insurance.claimservice.dto.PolicyDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICYSERVICE",path="/policy")
public interface PolicyClient {

		@GetMapping("/getPolicyById/{id}")
	    public PolicyDTO getPolicyById(@PathVariable Long id);
}