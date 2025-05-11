package com.insurance.policyservice.service;

import com.insurance.policyservice.feignClient.AgentClient;
import com.insurance.policyservice.feignClient.CustomerClient;
import com.insurance.policyservice.dto.AgentDTO;
import com.insurance.policyservice.dto.CustomerDTO;
import com.insurance.policyservice.exception.PolicyNotFoundException;
import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository repository;
    private final CustomerClient customerClient;
    private final AgentClient agentClient;

    // Add a new policy
    public Policy addPolicy(Policy policy) {
        log.info("Adding policy: {}", policy);
        return repository.save(policy);
    }

    // Get all policies
    public List<Policy> getAllPolicies() {
        log.info("Fetching all policies");
        return repository.findAll();
    }

    // Get policy by ID
    public Policy getPolicyById(Long id) {
        log.info("Fetching policy by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with ID: " + id));
    }

    // Update policy
    public Policy updatePolicy(Long id, Policy newPolicy) {
        log.info("Updating policy ID: {}", id);
        Policy existing = getPolicyById(id);
        existing.setPolicyName(newPolicy.getPolicyName());
        existing.setPolicyType(newPolicy.getPolicyType());
        existing.setPremiumAmount(newPolicy.getPremiumAmount());
        existing.setCoverageDetails(newPolicy.getCoverageDetails());
        existing.setValidityPeriod(newPolicy.getValidityPeriod());
        existing.setCustomerId(newPolicy.getCustomerId());
        existing.setAgentId(newPolicy.getAgentId());
        return repository.save(existing);
    }

    // Delete policy
    public String deletePolicy(Long id) {
        log.info("Deleting policy ID: {}", id);
        repository.deleteById(id);
        return "Policy deleted with ID: " + id;
    }

    // Get customer assigned to a policy
    public CustomerDTO getCustomerByPolicyId(Long id) {
        Policy policy = getPolicyById(id);
        log.info("Fetching customer with ID: {} for policy ID: {}", policy.getCustomerId(), id);
        return customerClient.getCustomerById(policy.getCustomerId());
    }

    // Get agent assigned to a policy
    public AgentDTO getAgentByPolicyId(Long id) {
        Policy policy = getPolicyById(id);
        log.info("Fetching agent with ID: {} for policy ID: {}", policy.getAgentId(), id);
        return agentClient.getAgentById(policy.getAgentId());
    }

    // Get all policies by agent
    public List<Policy> getPoliciesByAgentId(Long agentId) {
        log.info("Fetching policies for agent ID: {}", agentId);
        return repository.findByAgentId(agentId);
    }
}
