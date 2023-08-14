package com.location.api.server.domain;

import com.location.common.holder.CountHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Keyword {

    private Long keywordId;
    private String keyword;
    private Long count;

    @Builder
    public Keyword(Long keywordId,
                   String keyword,
                   Long count) {
        this.keywordId = keywordId;
        this.keyword = keyword;
        this.count = count;
    }

    public Keyword increaseCount(CountHolder countHolder) {
        this.count = countHolder.increase(this.count);
        return this;
    }
}
