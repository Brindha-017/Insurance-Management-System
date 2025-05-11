package com.insurance.notification.service;

import com.insurance.notification.model.Notification;

public interface NotificationService {
    String notify(Long policyId) throws Exception;
    String sendCustomNotification(Notification notification) throws Exception;
}