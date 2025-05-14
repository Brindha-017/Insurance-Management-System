package com.insurance.claimservice.controller;

import java.util.List; 

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claimservice.dto.ClaimDTO;
import com.insurance.claimservice.model.Claim;
import com.insurance.claimservice.service.ClaimService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/claims")
public class ClaimController {

    private ClaimService claimService;
    
    //Create or file a new claim by the customer or by the admin for the customer
    @PostMapping("/fileClaim")
    public Claim fileClaim(@Valid @RequestBody Claim claim) {
        return claimService.fileClaim(claim);
    }

    //update the status of claim from "FILED" to "UNDER_REVIEW"
    @PutMapping("/updateClaim/{id}")
    public Claim updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        return claimService.updateClaim(id, claim);
    }

    //Fetch the claim by using the claim Id
    @GetMapping("/getClaimById/{id}")
    public Claim getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    //fetch all claims to view its details
    @GetMapping("/getAllClaims")
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }
    
    //Delete a claim by using its Id
    @DeleteMapping("/deleteClaim/{id}")
    public String deleteClaim(@PathVariable Long id) {
        return claimService.deleteClaim(id);
    }

    //APPROVE or REJECT the claim 
    @GetMapping("/trackClaimStatus/{id}")
    public String trackStatus(@PathVariable Long id) {
        return claimService.trackClaimStatus(id);
    }
}
