
package com.insurance.claimservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.claimservice.dto.PolicyDTO;
import com.insurance.claimservice.exception.ClaimException;
import com.insurance.claimservice.feignClient.PolicyClient;
import com.insurance.claimservice.model.Claim;
import com.insurance.claimservice.repository.ClaimRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyClient policyClient;

    @Override
    public Claim fileClaim(Claim claim) {
        log.info("Filing claim for policy ID: {}", claim.getPolicyId());
        claim.setStatus("FILED");
        return claimRepository.save(claim);
    }

    @Override
    public Claim updateClaim(Long id, Claim updated) {
        Claim existing = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
        existing.setClaimAmount(updated.getClaimAmount());
        existing.setStatus(updated.getStatus());
        return claimRepository.save(existing);
    }

    @Override
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public String deleteClaim(Long id) {
        claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
        claimRepository.deleteById(id);
        return "Claim deleted with ID: " + id;
    }

    @Override
    public String trackClaimStatus(Long claimId) {
        Claim claim = getClaimById(claimId);
        PolicyDTO policy = policyClient.getPolicyById(claim.getPolicyId());

        String finalStatus;
        if (claim.getClaimAmount() >= policy.getPremiumAmount()) {
            finalStatus = "APPROVED";
        } else {
            finalStatus = "REJECTED";
        }

        claim.setStatus(finalStatus);
        claimRepository.save(claim);

        log.info("Claim ID: {} has status updated to {}", claimId, finalStatus);
        return finalStatus;
    }
}
