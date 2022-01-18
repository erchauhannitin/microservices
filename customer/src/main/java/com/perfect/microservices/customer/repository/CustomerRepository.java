package com.perfect.microservices.customer.repository;

import com.perfect.microservices.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.firstName like %?1% or c.lastName like %?1%")
    List<Customer> getMatchingCustomers(String name);
}
