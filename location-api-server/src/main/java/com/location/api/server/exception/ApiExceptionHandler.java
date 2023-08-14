package com.location.api.server.exception;

import com.location.common.code.ApiResponseCode;
import com.location.common.exception.LocationBadRequestException;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 일반오류
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e,
                                                       HttpServletRequest request) {
        log.error("ApiExceptionHandler > Exception:{}", e.toString(), e);

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.LOCATION_SYSTEM_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 잘못된 요청
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LocationBadRequestException.class)
    public ResponseEntity<ApiResponse> handleException(LocationBadRequestException e,
                                                       HttpServletRequest request) {
        log.error("ApiExceptionHandler > LOCATIONBadRequestException:{}", e.toString());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
