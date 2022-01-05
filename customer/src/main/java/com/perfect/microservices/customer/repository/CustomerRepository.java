package com.perfect.microservices.customer.repository;

import com.perfect.microservices.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
