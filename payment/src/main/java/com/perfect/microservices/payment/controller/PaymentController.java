package com.perfect.microservices.payment.controller;

import com.perfect.microservices.payment.model.Payment;
import com.perfect.microservices.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("dopayment")
    public void doPayment(@RequestBody Payment payment){
        log.info("Payment request {}", payment);
        paymentService.doPayment(payment);
    }

    @GetMapping("getPaymentDetails/{paymentId}")
    public Payment getPaymentDetails(@PathVariable("paymentId") String paymentId){
        log.info("getPaymentDetails request {}", paymentId);
        return paymentService.getPaymentDetails(paymentId);
    }

    @GetMapping("filter-check")
    public String filterCheck(){
        log.info("Filter Check request");
        return "Filter check";
    }

}
