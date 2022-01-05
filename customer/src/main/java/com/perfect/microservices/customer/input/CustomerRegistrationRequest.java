package com.perfect.microservices.customer.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
}
