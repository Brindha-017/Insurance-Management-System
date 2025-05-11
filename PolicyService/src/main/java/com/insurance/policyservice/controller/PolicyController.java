package com.insurance.policyservice.controller;

import com.insurance.policyservice.dto.AgentDTO; 
import com.insurance.policyservice.dto.CustomerDTO;
import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService service;

    @PostMapping("/savePolicy")
    public Policy addPolicy(@RequestBody Policy policy) {
        return service.addPolicy(policy);
    }

    @GetMapping("/getAllPolicies")
    public List<Policy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/getPolicyById/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PutMapping("/updatePolicy/{id}")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return service.updatePolicy(id, policy);
    }

    @DeleteMapping("/deletePolicy/{id}")
    public String deletePolicy(@PathVariable Long id) {
        return service.deletePolicy(id);
    }

    @GetMapping("/getCustomerByPolicyId/{id}")
    public CustomerDTO getCustomerByPolicyId(@PathVariable Long id) {
        return service.getCustomerByPolicyId(id);
    }

    @GetMapping("/getAgentByPolicyId/{id}")
    public AgentDTO getAgentByPolicyId(@PathVariable Long id) {
        return service.getAgentByPolicyId(id);
    }

    @GetMapping("/getPoliciesByAgentId/{agentId}")
    public List<Policy> getPoliciesByAgentId(@PathVariable Long agentId) {
        return service.getPoliciesByAgentId(agentId);
    }
}