package com.insurance.policyservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.insurance.policyservice.dto.AgentDTO;
import com.insurance.policyservice.dto.CustomerDTO;
import com.insurance.policyservice.exception.AgentNotFoundException;
import com.insurance.policyservice.exception.CustomerNotFoundException;
import com.insurance.policyservice.exception.GlobalExceptionHandler;
import com.insurance.policyservice.exception.PolicyNotFoundException;
import com.insurance.policyservice.feignClient.AgentClient;
import com.insurance.policyservice.feignClient.CustomerClient;
import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.repository.PolicyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository repository;
   
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
        existing.setCoverageAmount(newPolicy.getCoverageAmount());
        existing.setValidityPeriod(newPolicy.getValidityPeriod());
        return repository.save(existing);
    }

    // Delete policy
    public String deletePolicy(Long id) {
        log.info("Deleting policy ID: {}", id);
        repository.deleteById(id);
        return "Policy deleted with ID: " + id;
    }


    
    
   
}