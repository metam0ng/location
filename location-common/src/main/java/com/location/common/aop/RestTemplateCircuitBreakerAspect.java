package com.location.common.aop;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RestTemplateCircuitBreakerAspect {

    private final CircuitBreakerRegistry registry;

    @Around("execution(* org.springframework.web.client.RestTemplate.*(..)) && args(url,..)")
    public Object aspect(ProceedingJoinPoint pjp, String url) throws Throwable {
        return aspect(pjp, new URI(url));
    }

    @Around("execution(* org.springframework.web.client.RestTemplate.*(..)) && args(uri,..)")
    public Object aspect(ProceedingJoinPoint pjp, URI uri) throws Throwable {
        return registry.circuitBreaker(findHost(uri))
                .executeCheckedSupplier(pjp::proceed);
    }

    private String findHost(URI uri) {
        log.info("===============================circuit=========================== {}", Optional.ofNullable(uri)
                .map(URI::getHost)
                .orElse("default"));
        return Optional.ofNullable(uri)
                .map(URI::getHost)
                .orElse("default");
    }
}
