package com.perfect.microservices.payment.controller;

import com.perfect.microservices.payment.model.Payment;
import com.perfect.microservices.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("dopayment")
    public void doPayment(@RequestBody Payment payment){
        log.info("Payment request", payment);
        paymentService.doPayment(payment);
    }
}
