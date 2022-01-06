package com.perfect.microservices.payment.service;

import com.perfect.microservices.payment.model.Payment;
import com.perfect.microservices.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void doPayment(Payment payment) {

        log.info("Payment called ", payment);
        paymentRepository.save(payment);

    }
}
