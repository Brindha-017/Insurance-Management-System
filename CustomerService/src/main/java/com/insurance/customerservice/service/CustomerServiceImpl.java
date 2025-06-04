package com.insurance.customerservice.service;

import com.insurance.customerservice.dto.CustomerDTO; 
import com.insurance.customerservice.dto.PolicyDTO;
import com.insurance.customerservice.exception.PolicyNotFoundException;
import com.insurance.customerservice.exception.ResourceNotFoundException;
import com.insurance.customerservice.feignClient.PolicyClient;
import com.insurance.customerservice.model.Customer;
import com.insurance.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CustomerService interface.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    
    @Autowired
    private PolicyClient policyClient;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) {
        Customer customer = Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .street(dto.getStreet())
                .state(dto.getState())
                .city(dto.getCity())
                .postalcode(dto.getPostalcode())
                .build();
        Customer saved = customerRepository.save(customer);
        log.info("Customer saved with ID: {}", saved.getCustomerId());
        return mapToDTO(saved);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return mapToDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setStreet(dto.getStreet());
        customer.setState(dto.getState());
        customer.setCity(dto.getCity());
        customer.setPostalcode(dto.getPostalcode());
        customer.setAssignedPolicyId(dto.getAssignedPolicyId());


        Customer updated = customerRepository.save(customer);
        log.info("Customer updated with ID: {}", id);
        return mapToDTO(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        customerRepository.delete(customer);
        log.info("Customer deleted with ID: {}", id);
    }

    private CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .street(customer.getStreet())
                .state(customer.getState())
                .city(customer.getCity())
                .postalcode(customer.getPostalcode())
                .assignedPolicyId(customer.getAssignedPolicyId())
                .build();
    }
    
    @Override
    public Customer assignPolicyToCustomer(Long customerId, Long policyId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        try {
            PolicyDTO policy = policyClient.getPolicyById(policyId);
            if (policy == null || policy.getPolicyId() == null) {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (Exception e) {
            throw new PolicyNotFoundException("Policy service unavailable or policy not found.");
        }

        customer.setAssignedPolicyId(policyId);
        return customerRepository.save(customer);
    }

	@Override
	public List<Customer> getCustomersByPolicyId(Long policyId) {
		List<Customer> customers = customerRepository.findByAssignedPolicyId(policyId);
		 
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found for Policy ID: " + policyId);
        }
 
        return customers;
    
	}

	
}
