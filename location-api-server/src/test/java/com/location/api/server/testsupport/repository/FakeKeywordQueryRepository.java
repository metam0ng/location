package com.location.api.server.testsupport.repository;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.repository.query.KeywordQueryRepository;

import java.util.List;

public class FakeKeywordQueryRepository implements KeywordQueryRepository {
    @Override
    public List<KeywordResponse> findKeywordTop10() {
        return List.of(KeywordResponse.builder()
                        .keyword("카카오")
                        .count(10L)
                        .build(),
                KeywordResponse.builder()
                        .keyword("카카오 뱅크")
                        .count(7L)
                        .build(),
                KeywordResponse.builder()
                        .keyword("네이버")
                        .count(1L)
                        .build());
    }
}
