package com.location.api.server.repository.factory;

import com.location.api.server.domain.Keyword;
import com.location.repository.entity.KeywordEntity;

public class KeywordEntityFactory {

    private KeywordEntityFactory() {
    }

    public static KeywordEntity create(Keyword keyword) {
        return KeywordEntity.builder()
                .keywordId(keyword.getKeywordId())
                .keyword(keyword.getKeyword())
                .count(keyword.getCount())
                .version(keyword.getVersion())
                .build();
    }
}
