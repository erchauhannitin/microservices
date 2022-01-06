package com.perfect.microservices.customer.client;

import com.perfect.microservices.customer.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENT-CLIENT", url="http://localhost:8091")
public interface PaymentClient {

    @PostMapping("api/payment/dopayment")
    void doPayment(Payment payment);
}
