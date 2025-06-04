package com.insurance.agentservice.service;

import com.insurance.agentservice.model.Agent;
import com.insurance.agentservice.repository.AgentRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.*;

public class AgentServiceImplTest {

    @InjectMocks
    private AgentServiceImpl agentService;

    @Mock
    private AgentRepository agentRepository;

    private AutoCloseable closeable;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting tests for Agent module...");
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
        System.out.println("Finished all tests for Agent module.");
    }

    @Test
    void testCreateAgent() {
        Agent entity = new Agent();
        when(agentRepository.save(any(Agent.class))).thenReturn(entity);
        Assertions.assertNotNull(agentService.createAgent(entity));
    }

    @Test
    void testGetAllAgents() {
        when(agentRepository.findAll()).thenReturn(Collections.singletonList(new Agent()));
        Assertions.assertFalse(agentService.getAllAgents().isEmpty());
    }

    @Test
    void testGetAgentById() {
        Agent entity = new Agent();
        entity.setAgentId(1L);
        when(agentRepository.findById(1L)).thenReturn(Optional.of(entity));
        Assertions.assertEquals(1L, agentService.getAgentById(1L).getAgentId());
    }

}
