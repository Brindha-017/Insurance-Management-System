package com.insurance.agentservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.agentservice.model.Agent;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    // Custom query method to find agents by their assigned policy ID
    List<Agent> findByAssignedPolicyIdsContains(Long policyId);
}