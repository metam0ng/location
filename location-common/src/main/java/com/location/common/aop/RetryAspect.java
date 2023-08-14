package com.location.common.aop;

import com.location.common.annotation.RetryAfterThrowException;
import com.location.common.exception.LocationSearchException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(retryAfterThrowException)")
    public Object doRetry(ProceedingJoinPoint joinPoint, RetryAfterThrowException retryAfterThrowException) throws Throwable {
        int maxRetry = retryAfterThrowException.value();
        Exception exceptionHolder = null;
        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw new LocationSearchException();
    }

}
