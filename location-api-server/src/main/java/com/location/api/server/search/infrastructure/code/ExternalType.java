package com.location.api.server.search.infrastructure.code;

import com.location.common.code.DescriptionCode;
import com.location.external.client.spec.code.ApiType;

public enum ExternalType implements DescriptionCode {
    KAKAO("카카오", ApiType.KAKAO, 10, 10),
    NAVER("네이버", ApiType.NAVER, 5, 5);

    private final String description;
    private final ApiType apiType;
    private final int pageSize;
    private final int totalSize;

    public ApiType getApiType() {
        return this.apiType;
    }

    ExternalType(String description,
                 ApiType apiType,
                 int pageSize,
                 int totalSize) {
        this.description = description;
        this.apiType = apiType;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
    }

    @Override
    public String getCode() {
        return DescriptionCode.super.getCode();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalSize() {
        return this.totalSize;
    }
}
