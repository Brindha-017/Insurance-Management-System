package com.insurance.claimservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "claims")
@Getter
@Setter
@NoArgsConstructor
public class Claim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;
    
    @NotNull(message="Policy ID needs to be mentioned")
    private Long policyId;
    @NotNull(message="Customer ID needs to be mentioned")
    private Long customerId;
    @NotNull(message = "Enter the claim amount")
    private double claimAmount;
    private String status; // filed, under_review, approved, rejected
}
