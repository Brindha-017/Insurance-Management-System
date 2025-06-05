package com.insurance.agentservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.agentservice.model.Agent;
import com.insurance.agentservice.service.AgentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/agents")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    // Create an agent
    @PostMapping("/saveAgent")
    public Agent createAgent(@RequestBody Agent agent) {
    	log.info("API: Create Agent");
        return agentService.createAgent(agent);
    }

    // Update an agent
    @PutMapping("/updateAgent/{agentId}")
    public Agent updateAgent(@PathVariable Long agentId, @RequestBody Agent agent) {
    	log.info("API: Update Agent {}",agentId);
        return agentService.updateAgent(agentId, agent);
    }

    // Get agent by ID
    @GetMapping("/getAgentById/{agentId}")
    public Agent getAgentById(@PathVariable Long agentId) {
    	log.info("API: Get Agent By ID {}",agentId);
        return agentService.getAgentById(agentId);
    }

    // Get all agents
    @GetMapping("/getAllAgents")
    public List<Agent> getAllAgents() {
    	log.info("API: Get All Agents");
        return agentService.getAllAgents();
    }

    // Get agents by policy ID
    @GetMapping("/getAgentsByPolicyId/{policyId}")
    public List<Agent> getAgentsByPolicyId(@PathVariable Long policyId) {
    	log.info("API: Get Agents By Policy Id {}",policyId);
        return agentService.getAgentsByPolicyId(policyId);
    }
    @DeleteMapping("/deleteAgent/{id}")
    public String deleteAgent(@PathVariable Long id) {
    	log.info("API: Delete agent{}",id);
    	agentService.deleteAgent(id);
    	return "Agent with ID "+id+" deleted successfully"; 
    }
}




