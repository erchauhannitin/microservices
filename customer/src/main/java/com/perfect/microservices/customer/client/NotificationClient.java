package com.perfect.microservices.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "NOTIFICATION-CLIENT", url="${notification.service.url}")
public interface NotificationClient {

    @GetMapping("api/notification/email/{id}")
    void sendEmail(Integer id);
}
