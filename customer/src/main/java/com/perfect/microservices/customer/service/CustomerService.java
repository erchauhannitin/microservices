package com.perfect.microservices.customer.service;

import com.perfect.microservices.customer.config.CustomerConfig;
import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.model.Customer;
import com.perfect.microservices.customer.model.Payment;
import com.perfect.microservices.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired CustomerRepository customerRepository;
    @Autowired RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {

        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);
        Payment payment = Payment.builder()
                .id(customer.getId())
                .amount("100")
                .build();
        restTemplate.getForObject("http://localhost:8091/api/payment/dopayment", Payment.class, payment);

    }
}
