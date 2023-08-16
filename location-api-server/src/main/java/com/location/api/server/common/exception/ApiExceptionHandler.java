package com.location.api.server.common.exception;

import com.location.common.code.ApiResponseCode;
import com.location.common.exception.LocationBadRequestException;
import com.location.common.exception.LocationExternalApiException;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 일반오류
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        log.error("ApiExceptionHandler > Exception:{}", e.toString(), e);

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.LOCATION_SYSTEM_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 잘못된 요청
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LocationBadRequestException.class)
    public ResponseEntity<ApiResponse> handleException(LocationBadRequestException e) {
        log.error("ApiExceptionHandler > LOCATIONBadRequestException:{}", e.toString());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 외부 연동 실패
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ApiResponse> handleException(CallNotPermittedException e) {
        log.error("ApiExceptionHandler > CallNotPermittedException:{}", e.toString());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.ETC_SYSTEM_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 외부 연동 실패
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(LocationExternalApiException.class)
    public ResponseEntity<ApiResponse> handleException(LocationExternalApiException e) {
        log.error("ApiExceptionHandler > LocationExternalApiException:{}", e.toString());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.ETC_SYSTEM_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 요청 유효성 검사 실패
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleException(ConstraintViolationException e) {
        log.error("ApiExceptionHandler > ConstraintViolationException:{}", e.toString());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


}
