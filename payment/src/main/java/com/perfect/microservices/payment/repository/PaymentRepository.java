package com.perfect.microservices.payment.repository;

import com.perfect.microservices.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
