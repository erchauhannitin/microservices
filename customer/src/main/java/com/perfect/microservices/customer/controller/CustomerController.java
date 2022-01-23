package com.perfect.microservices.customer.controller;

import com.perfect.microservices.customer.exception.CustomerNotFoundException;
import com.perfect.microservices.customer.input.CustomerRegistrationRequest;
import com.perfect.microservices.customer.model.Customer;
import com.perfect.microservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("api/customer")
@RefreshScope
@EnableTransactionManagement
public class CustomerController {

    @Autowired CustomerService customerService;
    @Value("${message: shalom}") private String message;

    @PostMapping("register")
    public void registerCustomer(@Valid @RequestBody CustomerRegistrationRequest registrationRequest){
        log.info("New Customer registration {}", registrationRequest);
        customerService.registerCustomer(registrationRequest);
    }

    @GetMapping("/message")
    public String message(){
        log.info("Message is -----  {}", message);
        return message;
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@Valid @PathVariable String id){
        log.info("getCustomerById {}", id);
        final Optional<Customer> customer = customerService.getCustomerById(id);
        if(!customer.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }
        customer.get().add(linkTo(methodOn(CustomerService.class).getMatchingCustomers(id)).withSelfRel());
        return ResponseEntity.ok(customer.get());
    }

    @GetMapping("search/{name}")
    public List<Customer> getMatchingCustomers(@Valid @PathVariable String name){
        log.info("getMatchingCustomers {}", name);
        return customerService.getMatchingCustomers(name);
    }

}
