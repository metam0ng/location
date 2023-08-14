package com.location.common.exception;

public class LocationBadRequestException extends LocationException {

    public LocationBadRequestException(String msg) {
        super(msg);
    }

    public LocationBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
