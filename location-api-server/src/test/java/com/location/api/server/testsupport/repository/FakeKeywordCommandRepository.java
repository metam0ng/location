package com.location.api.server.testsupport.repository;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.repository.command.KeywordCommandRepository;

import java.util.Optional;

public class FakeKeywordCommandRepository implements KeywordCommandRepository {
    @Override
    public Keyword save(Keyword keyword) {
        return keyword;
    }

    @Override
    public Optional<Keyword> findByKeyword(String keyword) {
        if (keyword.contains("카카오 뱅크")) {
            return Optional.of(Keyword.builder()
                    .keywordId(1L)
                    .keyword(keyword)
                    .count(1L)
                    .version(0)
                    .build());
        }
        return Optional.empty();
    }
}
