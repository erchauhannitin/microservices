package com.perfect.microservices.payment;

import com.perfect.microservices.payment.filter.UrlPatternFilter;
import com.perfect.microservices.payment.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    FilterRegistrationBean<UrlPatternFilter> filterRegistrationBean(){
        final FilterRegistrationBean<UrlPatternFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new UrlPatternFilter());
        filterRegistrationBean.addUrlPatterns("/filter-check/*");
        return filterRegistrationBean;
    }
}
