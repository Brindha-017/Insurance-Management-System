package com.insurance.policyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentDTO {
    private Long agentId;
    private String agentName;
    private String contactInfo;
   
}