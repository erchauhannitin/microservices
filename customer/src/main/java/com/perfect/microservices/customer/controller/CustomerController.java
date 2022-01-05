package com.perfect.microservices.customer.controller;

import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("register")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest registrationRequest){
        log.info("New Customer registration", registrationRequest);
        customerService.registerCustomer(registrationRequest);
    }
}
