package com.location.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KeywordDto {

    private String keyword;

    private Long count;

    @Builder
    @QueryProjection
    public KeywordDto(String keyword,
                      Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
