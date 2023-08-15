package com.location.api.server.repository.factory;

import com.location.api.server.domain.Keyword;
import com.location.repository.entity.KeywordEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class KeywordEntityFactoryTest {

    @Test
    void Keyword로_KeywordEntity를_만들_수_있다() {
        // given
        Long id = 1L;
        String keyword = "카카오";
        Long count = 1L;
        int version = 0;
        Keyword target = Keyword.builder()
                .keywordId(id)
                .keyword(keyword)
                .count(count)
                .version(version)
                .build();

        // when
        KeywordEntity result = KeywordEntityFactory.create(target);

        // then
        Assertions.assertThat(result.getKeywordId()).isEqualTo(id);
        Assertions.assertThat(result.getKeyword()).isEqualTo(keyword);
        Assertions.assertThat(result.getCount()).isEqualTo(count);
        Assertions.assertThat(result.getVersion()).isEqualTo(version);
    }
}