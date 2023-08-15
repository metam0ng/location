package com.location.api.server.repository.query;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.testsupport.repository.FakeSearchKeywordQueryDslRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordQueryRepositoryImplTest {

    private KeywordQueryRepository keywordQueryRepository;

    @BeforeEach
    void setUp() {
        keywordQueryRepository = new KeywordQueryRepositoryImpl(new FakeSearchKeywordQueryDslRepository());
    }

    @Test
    void KeywordDto_List를_조회_할_수_있다() {
        // given
        // when
        List<KeywordResponse> result = keywordQueryRepository.findKeywordTop10();

        // then
        assertThat(result).isNotEmpty();
    }
}