package com.perfect.microservices.customer.client;

import com.perfect.microservices.customer.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENT-CLIENT", url="${payment.service.url}")
public interface PaymentClient {

    @PostMapping("api/payment/dopayment")
    void doPayment(Payment payment);
}
