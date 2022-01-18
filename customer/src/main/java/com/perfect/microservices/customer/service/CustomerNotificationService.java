package com.perfect.microservices.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.perfect.microservices.customer.client.NotificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerNotificationService {

    @Autowired NotificationClient notificationClient;

    @HystrixCommand(groupKey = "microservices", commandKey = "notification", fallbackMethod = "sendNotificationFallBack")
    public void sendNotification(Integer customerId) {
        log.info("Notification service called, sendEmail {}", customerId);
        notificationClient.sendEmail(customerId);
    }

    public void sendNotificationFallBack(Integer customerId){
        log.info("sendNotification Fallback called {}", customerId);
    }

}
