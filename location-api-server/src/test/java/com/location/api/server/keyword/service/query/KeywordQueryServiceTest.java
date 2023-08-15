package com.location.api.server.keyword.service.query;

import com.location.api.server.keyword.dto.response.KeywordResponse;
import com.location.api.server.keyword.service.query.KeywordQueryService;
import com.location.api.server.testsupport.repository.FakeKeywordQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordQueryServiceTest {

    private KeywordQueryService keywordQueryService;

    @BeforeEach
    void setUp() {
        keywordQueryService = new KeywordQueryService(new FakeKeywordQueryRepository());
    }

    @Test
    void KeywordResponse_List를_조회_할_수_있다() {
        // given
        // when
        List<KeywordResponse> result = keywordQueryService.findKeywordTop10();

        // then
        assertThat(result).isNotEmpty();
    }

}