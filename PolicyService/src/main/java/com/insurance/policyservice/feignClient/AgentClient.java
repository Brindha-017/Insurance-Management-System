package com.insurance.policyservice.feignClient;

import com.insurance.policyservice.dto.AgentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AgentService",path="/agents")
public interface AgentClient {
	@GetMapping("/getAgentById/{agentId}")
    public AgentDTO getAgentById(@PathVariable Long agentId);
}