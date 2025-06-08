package com.insurance.agentservice.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.stereotype.Service;

import com.insurance.agentservice.exception.AgentNotFoundException;
import com.insurance.agentservice.model.Agent;
import com.insurance.agentservice.repository.AgentRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {

   
    private AgentRepository agentRepository;

    // Create an agent
    @Override
    public Agent createAgent(Agent agent) {
        log.info("Creating new agent with ID: {}", agent.getAgentId());
        return agentRepository.save(agent);
    }

    // Update an agent
    @Override
    public Agent updateAgent(Long agentId, Agent agent) {
        log.info("Updating agent with ID: {}", agentId);
        Agent existingAgent = agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + agentId));
        existingAgent.setAgentName(agent.getAgentName());
        existingAgent.setContactInfo(agent.getContactInfo());
        existingAgent.setEmail(agent.getEmail());
        existingAgent.setAssignPolicyId(agent.getAssignPolicyId());
        return agentRepository.save(existingAgent);
    }

    // Get agent by ID
    @Override
    public Agent getAgentById(Long agentId) {
        log.info("Fetching agent with ID: {}", agentId);
        return agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + agentId));
    }

    // Get all agents
    @Override
    public List<Agent> getAllAgents() {
        log.info("Fetching all agents");
        return agentRepository.findAll();
    }

    // Get agents by policy ID
    @Override
    public List<Agent> getAgentsByPolicyId(Long policyId) {
        log.info("Fetching agents with policy ID: {}", policyId);
        return agentRepository.findByAssignPolicyId(policyId);
    }
    
    //Delete agents by agent id
    @Override
    public void deleteAgent(Long id) {
    	Agent agent =agentRepository.findById(id).orElseThrow(()-> new AgentNotFoundException("Agent not found with ID: "+id));
    	log.info("Deleting agent with ID: {}",id);
    	agentRepository.delete(agent);
    }


    @Override
    public Optional<Agent> findAgentByEmail(String email) {
        return agentRepository.findByEmail(email);
    }
}