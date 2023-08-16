package com.location.common.exception;

public class LocationExternalApiException extends LocationException {

    public LocationExternalApiException() {
        super("외부 시스템 연동에 실패하였습니다. 잠시 후 시도 부탁드리겠습니다.");
    }

    public LocationExternalApiException(String msg) {
        super(msg);
    }

    public LocationExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
