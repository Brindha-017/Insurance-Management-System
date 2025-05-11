package com.insurance.agentservice.model;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data // Lombok generates getters, setters, toString, etc.
@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;

    private String agentName;
    private String contactInfo;

    @ElementCollection
    private List<Long> assignedPolicyIds; // List of policy IDs assigned to this agent
}