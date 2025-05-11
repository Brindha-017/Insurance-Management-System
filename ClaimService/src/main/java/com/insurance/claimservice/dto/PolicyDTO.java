package com.insurance.claimservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PolicyDTO {
    private Long policyId;
    private Double premiumAmount;
}