package com.insurance.claimservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@RequiredArgsConstructor
public class ClaimDTO {
    private Long claimId;
    @NotNull(message="Policy ID needs to be mentioned")
    private Long policyId;
    @NotNull(message="Customer ID needs to be mentioned")
    private Long customerId;
    @NotNull(message="Enter the claim amount")
    private double claimAmount;
    private String status;
}

