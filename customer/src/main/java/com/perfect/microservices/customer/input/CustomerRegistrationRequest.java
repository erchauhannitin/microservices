package com.perfect.microservices.customer.input;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CustomerRegistrationRequest {

    @NotNull(message = "First name should not be null") private String firstName;
    private String lastName;
    @Email(message = "Enter valid email") private String email;
    private String mobileNumber;

    public boolean isValid(){
        return email != null || mobileNumber != null;
    }
}
