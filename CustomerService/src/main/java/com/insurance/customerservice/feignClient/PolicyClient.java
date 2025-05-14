package com.insurance.customerservice.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.insurance.customerservice.dto.PolicyDTO;


@FeignClient(name = "POLICYSERVICE",path="/policy")
public interface PolicyClient {

	@GetMapping("/getCustomerByPolicyId/{id}")
    public PolicyDTO getCustomerByPolicyId(@PathVariable Long id);
}
