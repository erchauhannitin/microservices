package com.perfect.notification.controller;

import com.perfect.notification.model.SmsNotification;
import com.perfect.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/notification")
@Slf4j
public class NotificationController {

    @Autowired NotificationService notificationService;

    @GetMapping("email/{id}")
    public void sendEmail(@Valid @PathVariable String id){
        log.info("Sending email for customer {}", id);
        final SmsNotification notification = new SmsNotification();
        notification.setCustomerId(Integer.parseInt(id));
        notificationService.sendEmail(notification);

    }
}
