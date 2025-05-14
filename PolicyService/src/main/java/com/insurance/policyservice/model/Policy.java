package com.insurance.policyservice.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;
	@NotBlank(message = "Policy name needs to be mentioned")
	@Column(nullable = false)
	private String policyName;
	@NotBlank(message = "Type of the policy shold be mentioned")
	private String policyType;
	@NotNull(message = "Enter the premium amount")
	private Double premiumAmount;
	@NotBlank(message = "Coverage Details shold be mentioned")
	private String coverageDetails;

	private LocalDate validityPeriod;

	private Long customerId;
	private Long agentId;
}
