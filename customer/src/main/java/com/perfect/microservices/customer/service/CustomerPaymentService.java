package com.perfect.microservices.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.perfect.microservices.customer.client.PaymentClient;
import com.perfect.microservices.customer.config.ClientTypeSettings;
import com.perfect.microservices.customer.exception.CustomerNotFoundException;
import com.perfect.microservices.customer.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Slf4j
@Service
public class CustomerPaymentService {

    @Autowired RestTemplate restTemplate;
    @Autowired ClientTypeSettings settings;
    @Autowired PaymentClient paymentClient;
    @Autowired @Qualifier("PaymentWebClient") WebClient webClient;
    @Value("${payment.service.url}")
    private String paymentUrl;
    @Value("${spring.security.user.name}")
    private String user;
    @Value("${spring.security.user.password}")
    private String password;

    @HystrixCommand(groupKey = "microservices", commandKey = "payment", fallbackMethod = "sendPaymentFallBack")
    public String sendPayment(Payment payment) {
        log.info("Feign client is {}", settings.isFeignClient());
        if(settings.isFeignClient()) {
            paymentClient.doPayment(payment);
        }

        log.info("RestTemplate client is {}", settings.isRestTemplate());
        if(settings.isRestTemplate()) {
            HttpHeaders headers = constructHeaders();
            HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);

            restTemplate.postForObject(paymentUrl +"/dopayment", entity, HttpEntity.class);
        }

        log.info("Web client is {}", settings.isWebClient());
        if(settings.isWebClient()) {
            webClient
                    .post()
                    .uri(paymentUrl + "/dopayment")
                    .bodyValue(payment)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CustomerNotFoundException("Customer not found")))
                    .bodyToMono(Payment.class)
                    .subscribe();
        }

        return "1";
    }

    public String sendPaymentFallBack(Payment payment){
        log.info("sendPayment Fallback called {}", payment);
        return "sendPayment Service not available";
    }

    private HttpHeaders constructHeaders() {
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + encodedAuth);
        return headers;
    }

}
