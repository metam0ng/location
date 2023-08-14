package com.location.common.code;

import lombok.ToString;

@ToString
public enum ApiResponseCode {

    SUCCESS                     ("2000", "OK"),

    LOCATION_SYSTEM_ERROR         ("1001", "장소 시스템 오류"),
    LOCATION_PERMISSION_ERROR     ("1002", "장소 권한 오류"),
    LOCATION_STATUS_ERROR         ("1003", "장소 상태 이상"),
    LOCATION_TRANSACTION_ERROR    ("1004", "트랜잭션 오류"),
    LOCATION_PUBLISH_ERROR        ("1101", "장소발행 오류"),
    LOCATION_USED_ERROR           ("1102", "장소사용 오류"),

    RESOURCE_NOT_FOUND          ("4004", "해당 리소스가 없음"),
    BUSINESS_ERROR              ("4005", "요구사항에 맞지 않음"),

    SERVICE_BLOCK_ERROR         ("8000", "시스템 점검"),

    BAD_REQUEST_ERROR           ("9000", "부적절한 요청 오류"),
    UNAUTHORIZED_ERROR          ("9001", "인증 오류"),
    ETC_SYSTEM_ERROR            ("9100", "연동 시스템 오류"),
    ETC_SYSTEM_FAILURE          ("9101", "연동 시스템 실패응답"),
    UNKNOWN_ERROR               ("9999", "알 수 없는 오류");

    private final String code;

    private final String defaultMessage;

    ApiResponseCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return this.code;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

}
