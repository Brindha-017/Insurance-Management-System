package com.insurance.customerservice;

import static org.mockito.Mockito.when; 

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.insurance.customerservice.model.Customer;
import com.insurance.customerservice.repository.CustomerRepository;
import com.insurance.customerservice.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    private AutoCloseable closeable;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting tests for Customer module...");
    }

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Finished all tests for Customer module.");
    }


    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(new Customer()));
        Assertions.assertFalse(customerService.getAllCustomers().isEmpty());
    }

    @Test
    void testGetCustomerById() {
        Customer entity = new Customer();
        entity.setCustomerId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(entity));
        Assertions.assertEquals(1L, customerService.getCustomerById(1L).getCustomerId());
    }
}

