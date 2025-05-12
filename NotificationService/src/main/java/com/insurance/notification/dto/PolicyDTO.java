package com.insurance.notification.dto;



import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class PolicyDTO {
    private Long policyId;
    private String policyName;
    private String validTill;
    private Long customerId;
}