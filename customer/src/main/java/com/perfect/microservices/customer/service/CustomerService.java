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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
    @Autowired WebClient webClient;
    @Autowired ClientTypeSettings settings;

    @HystrixCommand(groupKey = "microservices", commandKey = "payment", fallbackMethod = "paymentFallBack")
    public String registerCustomer(CustomerRegistrationRequest registrationRequest) {

        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

        log.info("Notification service called, sendEmail {}", customer.getId());
        notificationClient.sendEmail(customer.getId());

        Payment payment = Payment.builder()
                .id(customer.getId())
                .amount("100")
                .build();

        log.info("Feign client is {}", settings.isFeignClient());
        paymentClient.doPayment(payment);

        log.info("RestTemplate client is {}", settings.isRestTemplate());
        restTemplate.postForObject("dopayment", payment, Payment.class);

        log.info("Web client is {}", settings.isWebClient());
        webClient
                .post()
                .uri("dopayment")
                .bodyValue(payment)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CustomerNotFoundException("Customer not found")))
                .bodyToMono(Payment.class)
                .subscribe();

        return customer.getFirstName();
    }

    public String paymentFallBack(CustomerRegistrationRequest registrationRequest){
        log.info("Payment Fallback called");
        return "Payment Service not available";
    }

    public Optional<Customer> getCustomerById(String id){
        return customerRepository.findById(Integer.parseInt(id));
    }

    public List<Customer> getMatchingCustomers(String name){
        return customerRepository.getMatchingCustomers(name);
    }

}
