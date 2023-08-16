package com.location.api.server.testsupport.repository;

import com.location.repository.dto.KeywordDto;
import com.location.repository.repository.KeywordQueryDslRepository;

import java.util.List;

public class FakeKeywordQueryDslRepository implements KeywordQueryDslRepository {
    @Override
    public List<KeywordDto> findKeywordTop10() {
        return List.of(KeywordDto.builder()
                        .keyword("카카오")
                        .count(10L)
                        .build(),
                KeywordDto.builder()
                        .keyword("카카오 뱅크")
                        .count(7L)
                        .build(),
                KeywordDto.builder()
                        .keyword("네이버")
                        .count(1L)
                        .build());
    }
}
