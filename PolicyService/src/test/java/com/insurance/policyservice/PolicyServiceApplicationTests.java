package com.insurance.policyservice;

import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.repository.PolicyRepository;
import com.insurance.policyservice.service.PolicyService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolicyServiceTest {

    @Mock
    private PolicyRepository repository;

    @InjectMocks
    private PolicyService service;

    private Policy samplePolicy;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        samplePolicy = Policy.builder()
                .policyId(1L)
                .policyName("Life Secure")
                .policyType("Life")
                .premiumAmount(10000.0)
                .coverageDetails("Medical")
                .customerId(101L)
                .agentId(202L)
                .build();
    }

    @Test
    void testGetPolicyById() {
        when(repository.findById(1L)).thenReturn(Optional.of(samplePolicy));
        Policy result = service.getPolicyById(1L);
        assertEquals("Life Secure", result.getPolicyName());
    }

    @AfterAll
    void teardown() {
        samplePolicy = null;
    }
}