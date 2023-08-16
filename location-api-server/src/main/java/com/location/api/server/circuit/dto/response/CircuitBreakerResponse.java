package com.location.api.server.circuit.dto.response;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class CircuitBreakerResponse {
    private String name;
    private CircuitBreaker.State state;

    @Builder
    public CircuitBreakerResponse(String name,
                                  CircuitBreaker.State state) {
        this.name = name;
        this.state = state;
    }
}
