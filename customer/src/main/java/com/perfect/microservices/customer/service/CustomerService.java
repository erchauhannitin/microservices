package com.perfect.microservices.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.perfect.microservices.customer.client.NotificationClient;
import com.perfect.microservices.customer.client.PaymentClient;
import com.perfect.microservices.customer.config.ClientTypeSettings;
import com.perfect.microservices.customer.exception.CustomerNotFoundException;
import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.model.Customer;
import com.perfect.microservices.customer.model.Payment;
import com.perfect.microservices.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired CustomerRepository customerRepository;
    @Autowired RestTemplate restTemplate;
    @Autowired PaymentClient paymentClient;
    @Autowired NotificationClient notificationClient;
    @Autowired @Qualifier("PaymentWebClient") WebClient webClient;
    @Autowired @Qualifier("NotificationWebClient") WebClient notificationWebClient;
    @Autowired ClientTypeSettings settings;
    @Value("${payment.service.url}")
    private String paymentUrl;

    @Transactional
    public String registerCustomer(CustomerRegistrationRequest registrationRequest) {

        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

        final Integer customerId = customer.getId();
        sendNotification(customerId);

        Payment payment = Payment.builder()
                .id(customerId)
                .amount("100")
                .build();

        sendPayment(payment);

        return customer.getFirstName();
    }

    @HystrixCommand(groupKey = "microservices", commandKey = "payment", fallbackMethod = "sendPaymentFallBack")
    private void sendPayment(Payment payment) {
        log.info("Feign client is {}", settings.isFeignClient());
        if(settings.isFeignClient()) {
            paymentClient.doPayment(payment);
        }

        log.info("RestTemplate client is {}", settings.isRestTemplate());
        if(settings.isRestTemplate()) {
            restTemplate.postForObject(paymentUrl +"/dopayment", payment, Payment.class);
        }

        log.info("Web client is {}", settings.isWebClient());
        if(settings.isWebClient()) {
            webClient
                    .post()
                    .uri(paymentUrl + "/dopayment")
                    .bodyValue(payment)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CustomerNotFoundException("Customer not found")))
                    .bodyToMono(Payment.class)
                    .subscribe();
        }
    }

    @HystrixCommand(groupKey = "microservices", commandKey = "notification", fallbackMethod = "sendNotificationFallBack")
    private void sendNotification(Integer customerId) {
        log.info("Notification service called, sendEmail {}", customerId);
        notificationClient.sendEmail(customerId);
    }

    public Optional<Customer> getCustomerById(String id){
        return customerRepository.findById(Integer.parseInt(id));
    }

    public List<Customer> getMatchingCustomers(String name){
        return customerRepository.getMatchingCustomers(name);
    }

    public String sendNotificationFallBack(Integer customerId){
        log.info("sendNotification Fallback called {}", customerId);
        return "sendNotification Service not available";
    }

    public String sendPaymentFallBack(Payment payment){
        log.info("sendPayment Fallback called {}", payment);
        return "sendPayment Service not available";
    }
}
