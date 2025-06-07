package com.insurance.customerservice.controller;


import com.insurance.customerservice.dto.CustomerDTO; 
import com.insurance.customerservice.dto.PolicyDTO;
import com.insurance.customerservice.model.Customer;
import com.insurance.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for Customer operations.
 */
@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/saveCustomer")
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/getCustomerById/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getAllCustomers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    

    @PutMapping("/updateCustomer/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully.";
    }
    
    @PutMapping("/{customerId}/assignPolicy/{policyId}")
    public ResponseEntity<Customer> assignPolicyToCustomer(
            @PathVariable Long customerId,
            @PathVariable Long policyId) {

        Customer updated = customerService.assignPolicyToCustomer(customerId, policyId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    
    @GetMapping("/getCustomersByPolicyId/{policyId}")
    public List<Customer> customersByPolicyId(@PathVariable Long policyId){
    	return customerService.getCustomersByPolicyId(policyId);
    }
    
    @GetMapping("/getPolicy/{customerId}")
    public PolicyDTO getPolicy(@PathVariable Long customerId) {
    	return customerService.getPolicyOfCustomer(customerId);
    }
    @GetMapping("/findByEmail/{email}")
    public Optional<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.findCustomerByEmail(email);
    }
     
}
