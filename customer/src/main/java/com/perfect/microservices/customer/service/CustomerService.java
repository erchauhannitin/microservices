package com.perfect.microservices.customer.service;

import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.model.Customer;
import com.perfect.microservices.customer.model.Payment;
import com.perfect.microservices.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired CustomerRepository customerRepository;
    @Autowired CustomerPaymentService customerPaymentService;
    @Autowired CustomerNotificationService customerNotificationService;

    @Transactional
    public String registerCustomer(CustomerRegistrationRequest registrationRequest) {

        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

        final Integer customerId = customer.getId();
        customerNotificationService.sendNotification(customerId);

        Payment payment = Payment.builder()
                .id(customerId)
                .amount("100")
                .build();

        customerPaymentService.sendPayment(payment);
        return customer.getFirstName();
    }

    public Optional<Customer> getCustomerById(String id){
        return customerRepository.findById(Integer.parseInt(id));
    }

    public List<Customer> getMatchingCustomers(String name){
        return customerRepository.getMatchingCustomers(name);
    }

}
