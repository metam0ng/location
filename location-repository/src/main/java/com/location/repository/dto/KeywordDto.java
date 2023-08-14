package com.location.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KeywordDto {

    private String keyword;

    private Long count;

    @QueryProjection
    public KeywordDto(String keyword,
                      Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
