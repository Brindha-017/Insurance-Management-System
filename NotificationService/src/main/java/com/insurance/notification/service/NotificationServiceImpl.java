package com.insurance.notification.service;

import com.insurance.notification.model.Notification;
import com.insurance.notification.feignClient.PolicyClient;
import com.insurance.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository repository;
	private final PolicyClient policyClient;
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	// Notify about policy validity
	public String notify(Long policyId) throws Exception {
		var policy = policyClient.getPolicyById(policyId);
		if (policy == null)
			throw new Exception("Policy not found");

		Notification notification = new Notification();
		notification.setPolicyId(policy.getPolicyId());
		notification.setCustomerId(policy.getCustomerId());
		notification.setMessage("Your policy '" + policy.getPolicyName() + "' is valid till " + policy.getValidTill());
		notification.setDate(LocalDate.now().toString());

		repository.save(notification);
		logger.info("Notification sent for policy {}", policyId);
		return "Notification sent";
	}

	// Send a custom notification
	public String sendCustomNotification(Notification notification) throws Exception {
		if (notification.getMessage() == null || notification.getCustomerId() == null) {
			throw new Exception("Invalid notification request");
		}

		notification.setDate(LocalDate.now().toString());
		repository.save(notification);
		logger.info("Custom notification sent to customer {}", notification.getCustomerId());
		return "Custom notification sent";
	}
}
