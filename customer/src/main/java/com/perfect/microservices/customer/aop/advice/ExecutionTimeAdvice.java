package com.perfect.microservices.customer.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ExecutionTimeAdvice {

    @Around("@annotation(com.perfect.microservices.customer.aop.advice.ExecutionTime)")
    public Object executionTime(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        final Object obj = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Exection time is {} milliseconds for {}", endTime-startTime, pjp.getSignature());
        return obj;
    }
}
