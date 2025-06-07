package com.insurance.customerservice.service;

import com.insurance.customerservice.dto.CustomerDTO;
import com.insurance.customerservice.dto.PolicyDTO;
import com.insurance.customerservice.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Customer service with method declarations.
 */
public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();
    
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
    
    Customer assignPolicyToCustomer(Long customerId, Long policyId);
    
    PolicyDTO getPolicyOfCustomer(Long customerId);

	List<Customer> getCustomersByPolicyId(Long policyId);

	Optional<Customer> findCustomerByEmail(String email);

	
}
