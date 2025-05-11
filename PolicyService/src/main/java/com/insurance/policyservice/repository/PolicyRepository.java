package com.insurance.policyservice.repository;

import com.insurance.policyservice.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByAgentId(Long agentId);
}
