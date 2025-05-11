package com.insurance.claimservice.dto;

import lombok.*;


@Data
@RequiredArgsConstructor
public class ClaimDTO {
    private Long claimId;
    private Long policyId;
    private Long customerId;
    private double claimAmount;
    private String status;
}

