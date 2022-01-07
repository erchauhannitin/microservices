package com.perfect.microservices.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DetailsNotFoundException extends ResponseStatusException {

    public DetailsNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
    
}
