package com.insurance.claimservice.repository;

import com.insurance.claimservice.dto.ClaimDTO;
import com.insurance.claimservice.model.Claim;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

	ClaimDTO save(ClaimDTO claim);
	
	List<Claim> findByCustomerId(Long customerId);
}