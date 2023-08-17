package com.location.common.exception;

public class LocationExternalApiNotFoundException extends LocationException {

    public LocationExternalApiNotFoundException() {
        super("외부 연동 Api 대상을 찾지 못했습니다.");
    }

    public LocationExternalApiNotFoundException(String msg) {
        super(msg);
    }

    public LocationExternalApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
