package com.perfect.microservices.customer.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@Entity
public class Customer  extends RepresentationModel<Customer> {

    @Id
    @SequenceGenerator(name = "customer_id_sequence",
            sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Customer(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
