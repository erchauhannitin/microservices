package payment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.model.Payment;
import payment.repository.PaymentRepository;

@Service
@Slf4j
public class PaymentService {

    @Autowired PaymentRepository paymentRepository;

    public void doPayment(Payment payment) {

        log.info("Payment called ", payment);
        paymentRepository.save(payment);

    }
}
