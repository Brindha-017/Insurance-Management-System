package com.insurance.customerservice.repository;

import com.insurance.customerservice.model.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Customer model.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByAssignedPolicyId(Long policyId);

}
