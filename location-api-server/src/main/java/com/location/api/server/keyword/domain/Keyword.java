package com.location.api.server.keyword.domain;

import com.location.common.holder.CountHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Keyword {

    private Long keywordId;
    private String keyword;
    private Long count;
    private int version;

    @Builder
    public Keyword(Long keywordId,
                   String keyword,
                   Long count,
                   int version) {
        this.keywordId = keywordId;
        this.keyword = keyword;
        this.count = count;
        this.version = version;
    }

    public Keyword increaseCount(CountHolder countHolder) {
        this.count = countHolder.increase(this.count);
        return this;
    }
}
