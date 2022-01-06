package com.perfect.microservices.customer.controller;

import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/customer")
@RefreshScope
public class CustomerController {

    @Autowired CustomerService customerService;
    @Value("${message: shalom}") private String message;

    @PostMapping("register")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest registrationRequest){
        log.info("New Customer registration", registrationRequest);
        customerService.registerCustomer(registrationRequest);
    }

    @GetMapping("/message")
    public String message(){
        log.info("Message is ----- ", message);
        return message;
    }
}
