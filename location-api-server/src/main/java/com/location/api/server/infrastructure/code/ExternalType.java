package com.location.api.server.infrastructure.code;

import com.location.common.code.DescriptionCode;
import com.location.external.client.spec.code.ApiType;

public enum ExternalType implements DescriptionCode {
    KAKAO("카카오", ApiType.KAKAO),
    NAVER("네이버", ApiType.NAVER),
    EMPTY("없음", null),;

    private final String description;
    private final ApiType apiType;

    public ApiType getApiType() {
        return this.apiType;
    }

    ExternalType(String description,
                 ApiType apiType) {
        this.description = description;
        this.apiType = apiType;
    }

    @Override
    public String getCode() {
        return DescriptionCode.super.getCode();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
