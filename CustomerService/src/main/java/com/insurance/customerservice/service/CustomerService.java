package com.insurance.customerservice.service;

import com.insurance.customerservice.dto.CustomerDTO;
import com.insurance.customerservice.dto.PolicyDTO;

import java.util.List;

/**
 * Interface for Customer service with method declarations.
 */
public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();
    
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

	
}
