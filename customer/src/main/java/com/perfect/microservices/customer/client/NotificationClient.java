package com.perfect.microservices.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "NOTIFICATION-CLIENT", url="${notification.service.url}")
public interface NotificationClient {

    @GetMapping("api/notification/email/{id}")
    void sendEmail(@PathVariable("id") Integer id);
}
