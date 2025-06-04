package com.insurance.policyservice;

import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.repository.PolicyRepository;
import com.insurance.policyservice.service.PolicyServiceImpl;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.*;

public class PolicyServiceImplTest {

    @InjectMocks
    private PolicyServiceImpl policyService;

    @Mock
    private PolicyRepository policyRepository;

    private AutoCloseable closeable;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting tests for Policy module...");
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
        System.out.println("Finished all tests for Policy module.");
    }

    @Test
    void testGetAllPolicys() {
        when(policyRepository.findAll()).thenReturn(Collections.singletonList(new Policy()));
        Assertions.assertFalse(policyService.getAllPolicies().isEmpty());
    }

    @Test
    void testGetPolicyById() {
        Policy entity = new Policy();
        entity.setPolicyId(1L);
        when(policyRepository.findById(1L)).thenReturn(Optional.of(entity));
        Assertions.assertEquals(1L, policyService.getPolicyById(1L).getPolicyId());
    }

    @Test
    void testDeletePolicy() {
        doNothing().when(policyRepository).deleteById(1L);
        policyService.deletePolicy(1L);
        verify(policyRepository, times(1)).deleteById(1L);
    }
}
