package com.perfect.microservices.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class CustomerConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Qualifier("PaymentWebClient")
    public WebClient getPaymentWebClient(){
        return WebClient.builder()
                .baseUrl("https://PAYMENT-SERVICE/api/payment/")
                .filter(logRequest())
                .filter(logRequest())
                .build();
    }

    @Bean
    @Qualifier("NotificationWebClient")
    public WebClient getNotificationWebClient(){
        return WebClient.builder()
                .baseUrl("https://NOTIFICATION-SERVICE/api/notification/")
                .build();
    }


    private ExchangeFilterFunction logRequest(){

        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
          log.info("Request -> {} {}", clientRequest.url(), clientRequest.body());
          return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse(){

        return ExchangeFilterFunction.ofRequestProcessor(clientResponse -> {
            log.info("Response -> {} {}", clientResponse.url(), clientResponse.body());
            return Mono.just(clientResponse);
        });
    }

}
