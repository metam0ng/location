package com.location.api.server.circuit.controller;

import com.location.api.server.circuit.dto.response.CircuitBreakerResponse;
import com.location.api.server.circuit.service.CircuitBreakerService;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/locations")
public class CircuitBreakerController {

    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/circuit/close")
    public ResponseEntity<ApiResponse<Void>> close(@RequestParam @NotBlank String name) {
        //test
        circuitBreakerService.close(name);
        ApiResponse<Void> apiResponse = ApiResponseGenerator.success();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/circuit/open")
    public ResponseEntity<ApiResponse<Void>> open(@RequestParam @NotBlank String name) {
        circuitBreakerService.open(name);
        ApiResponse<Void> apiResponse = ApiResponseGenerator.success();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/circuit/status")
    public ResponseEntity<ApiResponse<CircuitBreaker.State>> status(@RequestParam @NotBlank String name) {
        CircuitBreaker.State state = circuitBreakerService.status(name);
        ApiResponse<CircuitBreaker.State> apiResponse = ApiResponseGenerator.success(state);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/circuit/all")
    public ResponseEntity<ApiResponse<List<CircuitBreakerResponse>>> all() {
        List<CircuitBreakerResponse> response = circuitBreakerService.all();
        ApiResponse<List<CircuitBreakerResponse>> apiResponse = ApiResponseGenerator.success(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
