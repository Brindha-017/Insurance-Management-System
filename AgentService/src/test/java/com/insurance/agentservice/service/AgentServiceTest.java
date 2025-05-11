package com.insurance.agentservice.service;


import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.insurance.agentservice.model.Agent;
import com.insurance.agentservice.repository.AgentRepository;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgentServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @InjectMocks
    private AgentService agentService;

    private Agent agent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        agent = new Agent();
        agent.setAgentId(1L);
        agent.setAgentName("John Doe");
        agent.setContactInfo("john.doe@example.com");
        agent.setAssignedPolicyIds(List.of(1001L, 1002L));
    }

    @Test
    public void testCreateAgent() {
        when(agentRepository.save(agent)).thenReturn(agent);
        Agent createdAgent = agentService.createAgent(agent);
        assertNotNull(createdAgent);
        assertEquals("John Doe", createdAgent.getAgentName());
    }

    @Test
    public void testGetAgentById() {
        when(agentRepository.findById(1L)).thenReturn(java.util.Optional.of(agent));
        Agent fetchedAgent = agentService.getAgentById(1L);
        assertNotNull(fetchedAgent);
        assertEquals("John Doe", fetchedAgent.getAgentName());
    }

    @Test
    public void testGetAllAgents() {
        when(agentRepository.findAll()).thenReturn(List.of(agent));
        List<Agent> agents = agentService.getAllAgents();
        assertNotNull(agents);
        assertFalse(agents.isEmpty());
    }

    @Test
    public void testUpdateAgent() {
        when(agentRepository.findById(1L)).thenReturn(java.util.Optional.of(agent));
        agent.setAgentName("Jane Doe");
        when(agentRepository.save(agent)).thenReturn(agent);
        Agent updatedAgent = agentService.updateAgent(1L, agent);
        assertNotNull(updatedAgent);
        assertEquals("Jane Doe", updatedAgent.getAgentName());
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources after each test
    }
}
