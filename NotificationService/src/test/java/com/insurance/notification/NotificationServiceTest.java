package com.insurance.notification;

import com.insurance.notification.model.Notification;
import com.insurance.notification.repository.NotificationRepository;
import com.insurance.notification.service.NotificationServiceImpl;
import com.insurance.notification.feignClient.PolicyClient;
import com.insurance.notification.dto.PolicyDTO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @InjectMocks
    private NotificationServiceImpl service;

    @Mock
    private NotificationRepository repository;

    @Mock
    private PolicyClient policyClient;

    private AutoCloseable closeable;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting NotificationService Tests");
    }

    @BeforeEach
    void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotify() throws Exception {
        PolicyDTO policy = new PolicyDTO();
        policy.setPolicyId(1L);
        policy.setPolicyName("Life Cover");
        policy.setValidityPeriod("2025-12-31");
        policy.setCustomerId(101L);

        when(policyClient.getPolicyById(1L)).thenReturn(policy);
        service.notify(1L);

        verify(repository, times(1)).save(any());
    }

    @Test
    void testSendCustomNotification() throws Exception {
        Notification notification = new Notification();
        notification.setCustomerId(101L);
        notification.setMessage("Reminder: Your policy will expire soon.");

        service.sendCustomNotification(notification);
        verify(repository, times(1)).save(any());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Completed NotificationService Tests");
    }
}