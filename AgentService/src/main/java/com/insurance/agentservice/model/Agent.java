package com.insurance.agentservice.model;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Data // Lombok generates getters, setters, toString, etc.
@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;
    @NotBlank(message="Agent Name is required")
    private String agentName;
    @NotBlank(message="Contact info of the agent is required")
    private String contactInfo;

    private Long assignPolicyId; // List of policy IDs assigned to this agent
}