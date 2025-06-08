
package com.insurance.claimservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.claimservice.dto.ClaimDTO;
import com.insurance.claimservice.dto.PolicyDTO;
import com.insurance.claimservice.exception.ClaimException;
import com.insurance.claimservice.exception.CustomerNotFoundException;
import com.insurance.claimservice.exception.PolicyNotFoundException;
import com.insurance.claimservice.feignClient.CustomerClient;
import com.insurance.claimservice.feignClient.PolicyClient;
import com.insurance.claimservice.model.Claim;
import com.insurance.claimservice.repository.ClaimRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    @Autowired
    private PolicyClient policyClient;
    
    @Autowired
    private CustomerClient customerClient;

    //Create or file a new claim by the customer or by the admin to the customer
    @Override
    public Claim fileClaim(Claim claim) {
        log.info("Filing claim for policy ID: {}", claim.getPolicyId());
      //Validate Customer
        try {
			customerClient.getCustomerById(claim.getCustomerId());
		} catch (Exception e) {
			log.error("Invalid Customer ID: {}", claim.getCustomerId(), e);
			throw new CustomerNotFoundException("Invalid Customer ID: " + claim.getCustomerId());
		}
 
		// Validate agent
        try {
			policyClient.getPolicyById(claim.getPolicyId());
		} catch (Exception e) {
			log.error("Invalid Policy ID: {}", claim.getPolicyId(), e);
			throw new PolicyNotFoundException("Invalid Policy ID: " + claim.getPolicyId());
		}
        claim.setStatus("FILED");
        return claimRepository.save(claim);
    }

    //update the status of claim from "FILED" to "UNDER_REVIEW"
    @Override
    public Claim updateClaim(Long id, Claim updated) {
        Claim existing = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
        existing.setStatus(updated.getStatus());
        return claimRepository.save(existing);
    }

    //Fetch the claim by using the claim Id
    @Override
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
    }

    //fetch all claims to view its details
    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    //Delete a claim by using its Id
    @Override
    public String deleteClaim(Long id) {
        claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException("Claim not found with id: " + id));
        claimRepository.deleteById(id);
        return "Claim deleted with ID: " + id;
    }

    //APPROVE or REJECT the claim by comparing it to the premiumAmount in policy by its policyId 
    @Override
    public String trackClaimStatus(Long claimId) {
        Claim claim = getClaimById(claimId);
        PolicyDTO policy = policyClient.getPolicyById(claim.getPolicyId());

        String finalStatus;
        if (claim.getClaimAmount() >= policy.getPremiumAmount()&&claim.getClaimAmount()<=policy.getCoverageAmount()) {
            finalStatus = "APPROVED";
        } else {
            finalStatus = "REJECTED";
        }

        claim.setStatus(finalStatus);
        claimRepository.save(claim);

        log.info("Claim ID: {} has status updated to {}", claimId, finalStatus);
        return finalStatus;
    }

	@Override
	public List<Claim> getClaimofCustomer(Long customerId) {
		List<Claim> claims = claimRepository.findByCustomerId(customerId);
	    if (claims.isEmpty()) {
	        log.warn("No claims found for customer ID: {}", customerId);
	        throw new RuntimeException("No claims found for this customer");
	    }
	    log.info("Fetched {} claims for customer ID {}", claims.size(), customerId);
	    return claims;
	}

	@Override
	public List<Claim> getClaimByPolicy(Long policyId) {
		return claimRepository.findByPolicyId(policyId);
	}
}
