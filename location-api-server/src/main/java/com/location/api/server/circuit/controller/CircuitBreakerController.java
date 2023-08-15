package com.location.api.server.circuit.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.collection.Seq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/locations")
public class CircuitBreakerController {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/circuit/close")
    public ResponseEntity<Void> close(@RequestParam String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToClosedState();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/circuit/open")
    public ResponseEntity<Void> open(@RequestParam String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToOpenState();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/circuit/status")
    public ResponseEntity<CircuitBreaker.State> status(@RequestParam String name) {
        CircuitBreaker.State state = circuitBreakerRegistry.circuitBreaker(name)
                .getState();
        return ResponseEntity.ok(state);
    }

    @GetMapping("/circuit/all")
    public ResponseEntity<Void> all() {
        Seq<CircuitBreaker> circuitBreakers = circuitBreakerRegistry.getAllCircuitBreakers();
        for (CircuitBreaker circuitBreaker : circuitBreakers) {
            log.error("circuitName={}, state={}", circuitBreaker.getName(), circuitBreaker.getState());
        }
        return ResponseEntity.ok().build();
    }
}
