package com.perfect.microservices.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("client.type")
public class ClientTypeSettings {

    private boolean feignClient;
    private boolean restTemplate;
    private boolean webClient;

}
