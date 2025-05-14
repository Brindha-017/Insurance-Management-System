package com.insurance.customerservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PolicyDTO {
	
	private Long policyId;
	
	private String policyName;

	private String policyType;

	private Double premiumAmount;

	private String coverageDetails;
}
