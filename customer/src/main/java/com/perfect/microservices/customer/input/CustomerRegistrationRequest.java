package com.perfect.microservices.customer.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {

    @NotNull(message = "First name should not be null") private String firstName;
    private String lastName;
    @Email(message = "Enter valid email") private String email;
    private String mobileNumber;

    public boolean isValid(){
        return email != null || mobileNumber != null;
    }
}
