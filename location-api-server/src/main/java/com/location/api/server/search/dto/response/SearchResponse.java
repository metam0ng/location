package com.location.api.server.search.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class SearchResponse {

    private String name;
    private String address;
    private String roadAddress;

    @Builder
    public SearchResponse(String name,
                          String address,
                          String roadAddress) {
        this.name = name;
        this.address = address;
        this.roadAddress = roadAddress;
    }
}
