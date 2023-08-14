package com.location.common.exception;

public class LocationException extends RuntimeException {

    public LocationException(String msg) {
        super(msg);
    }

    public LocationException(String message, Throwable cause) {
        super(message, cause);
    }

}
