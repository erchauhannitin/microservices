package com.perfect.notification.service;

import com.perfect.notification.model.SmsNotification;
import com.perfect.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired NotificationRepository notificationRepository;

    public void sendEmail(SmsNotification notification) {
        notificationRepository.save(notification);
    }

}
