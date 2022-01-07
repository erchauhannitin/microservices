package com.perfect.microservices.payment.service;

import com.perfect.microservices.payment.exception.DetailsNotFoundException;
import com.perfect.microservices.payment.model.Payment;
import com.perfect.microservices.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void doPayment(Payment payment) {
        log.info("Payment called {}", payment);
        paymentRepository.save(payment);

    }

    public Payment getPaymentDetails(String paymentId) {
        log.info("getPaymentDetails called {}", paymentId);
        return paymentRepository.findById(Integer.valueOf(paymentId))
                .orElseThrow(() -> new DetailsNotFoundException(HttpStatus.NOT_FOUND, "Payment details not found"));
    }
}
