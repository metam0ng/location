package com.location.api.server.circuit.service;

import com.location.api.server.circuit.dto.response.CircuitBreakerResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.collection.Seq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CircuitBreakerService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public void close(String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToClosedState();
    }

    public void open(String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToOpenState();
    }

    public CircuitBreaker.State status(String name) {
        return circuitBreakerRegistry.circuitBreaker(name)
                .getState();
    }

    public List<CircuitBreakerResponse> all() {
        Seq<CircuitBreaker> circuitBreakers = circuitBreakerRegistry.getAllCircuitBreakers();
        List<CircuitBreakerResponse> responses = new ArrayList<>();
        for (CircuitBreaker circuitBreaker : circuitBreakers) {
            responses.add(CircuitBreakerResponse.builder()
                    .name(circuitBreaker.getName())
                    .state(circuitBreaker.getState())
                    .build());

        }
        return responses;
    }
}
