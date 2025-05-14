package com.insurance.claimservice.service;


import com.insurance.claimservice.model.Claim;

import jakarta.validation.Valid;

import java.util.List;

public interface ClaimService {
	Claim updateClaim(Long id, Claim claim);

	Claim getClaimById(Long id);

	List<Claim> getAllClaims();

	String deleteClaim(Long id);

	String trackClaimStatus(Long claimId);

	Claim fileClaim(@Valid Claim claim);
}