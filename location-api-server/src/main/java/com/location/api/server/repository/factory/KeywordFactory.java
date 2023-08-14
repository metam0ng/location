package com.location.api.server.repository.factory;

import com.location.api.server.domain.Keyword;
import com.location.common.holder.CountHolder;
import com.location.repository.entity.KeywordEntity;

public class KeywordFactory {

    private KeywordFactory() {
    }

    public static Keyword create(KeywordEntity keywordEntity) {
        return Keyword.builder()
                .keywordId(keywordEntity.getKeywordId())
                .keyword(keywordEntity.getKeyword())
                .count(keywordEntity.getCount())
                .version(keywordEntity.getVersion())
                .build();
    }

    public static Keyword create(String keyword,
                                 CountHolder countHolder) {
        return Keyword.builder()
                .keyword(keyword)
                .count(countHolder.one())
                .build();
    }

}
