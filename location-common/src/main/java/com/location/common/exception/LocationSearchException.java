package com.location.common.exception;

public class LocationSearchException extends LocationException {

    public LocationSearchException() {
        super("검색서비스 검색 오류");
    }

    public LocationSearchException(String msg) {
        super(msg);
    }

    public LocationSearchException(String message, Throwable cause) {
        super(message, cause);
    }

}
