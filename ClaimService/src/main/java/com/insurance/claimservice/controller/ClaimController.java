package com.insurance.claimservice.controller;

import com.insurance.claimservice.model.Claim;
import com.insurance.claimservice.service.ClaimService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping("/fileClaim")
    public Claim fileClaim(@RequestBody Claim claim) {
        return claimService.fileClaim(claim);
    }

    @PutMapping("/updateClaim/{id}")
    public Claim updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        return claimService.updateClaim(id, claim);
    }

    @GetMapping("/getClaimById/{id}")
    public Claim getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    @GetMapping("/getAllClaims")
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @DeleteMapping("/deleteClaim/{id}")
    public String deleteClaim(@PathVariable Long id) {
        return claimService.deleteClaim(id);
    }

    @GetMapping("/trackClaimStatus/{id}")
    public String trackStatus(@PathVariable Long id) {
        return claimService.trackClaimStatus(id);
    }
}
