package com.insurance.agentservice.service;

import java.util.List;
import java.util.Optional;

import com.insurance.agentservice.model.Agent;

public interface AgentService {
	Agent createAgent(Agent agent);

	Agent updateAgent(Long agentId, Agent agent);

	Agent getAgentById(Long agentId);

	List<Agent> getAllAgents();

	List<Agent> getAgentsByPolicyId(Long policyId);

	void deleteAgent(Long id);
	
	Optional<Agent> findAgentByEmail(String email);
}
