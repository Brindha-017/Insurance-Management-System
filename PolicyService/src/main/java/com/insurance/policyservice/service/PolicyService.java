package com.insurance.policyservice.service;

import java.util.List;

import com.insurance.policyservice.dto.AgentDTO;
import com.insurance.policyservice.dto.CustomerDTO;
import com.insurance.policyservice.model.Policy;

public interface PolicyService {
	Policy addPolicy(Policy policy);
	List<Policy> getAllPolicies();
	Policy getPolicyById(Long id);
	Policy updatePolicy(Long id, Policy newPolicy);
	String deletePolicy(Long id);
	CustomerDTO getCustomerByPolicyId(Long id);
	AgentDTO getAgentByPolicyId(Long id);
}
