package com.insurance.policyservice.feignClient;
import java.util.*;

import com.insurance.policyservice.dto.CustomerDTO;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMERSERVICE",path="/customers")
public interface CustomerClient {
	@GetMapping("/getAllCustomers")
    public List<CustomerDTO> getAllCustomers();
	
}
