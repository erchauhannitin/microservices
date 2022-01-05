package com.perfect.microservices.customer.service;

import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.model.Customer;
import com.perfect.microservices.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {

        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();

        customerRepository.save(customer);

    }
}
