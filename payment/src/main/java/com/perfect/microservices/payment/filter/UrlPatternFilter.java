package com.perfect.microservices.payment.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Order(3)
public class UrlPatternFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Url Pattern filter called");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
