package com.perfect.microservices.customer.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAdvice {

    @Pointcut(value = "execution(* com.perfect.microservices.customer.*.*.*(..))")
    private void loggingPointCut(){

    }

    @Around("loggingPointCut()")
    public Object logger(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Method invoked {} for class {}", pjp.getSignature().getName(), pjp.getTarget().getClass().toGenericString());
        final Object response = pjp.proceed();
        log.info("Response {}", response);
        return response;
    }
}
