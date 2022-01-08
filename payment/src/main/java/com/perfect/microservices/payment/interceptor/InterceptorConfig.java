package com.perfect.microservices.payment.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new GenericInterceptor()).order(2);
        registry.addInterceptor(new UrlPatternInterceptor()).addPathPatterns("/api/**").order(1);
    }

}
