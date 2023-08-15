package com.location.api.server.keyword.dto.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class KeywordResponse {


    private String keyword;

    private Long count;

    @Builder
    public KeywordResponse(String keyword,
                           Long count) {
        this.keyword = keyword;
        this.count = count;
    }

}
