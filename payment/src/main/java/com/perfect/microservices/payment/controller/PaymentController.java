package payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.model.Payment;
import payment.service.PaymentService;

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
