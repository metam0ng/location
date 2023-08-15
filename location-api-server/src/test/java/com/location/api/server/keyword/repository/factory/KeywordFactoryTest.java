package com.location.api.server.keyword.repository.factory;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.repository.factory.KeywordFactory;
import com.location.api.server.testsupport.service.FakeCountHolder;
import com.location.common.holder.CountHolder;
import com.location.repository.entity.KeywordEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordFactoryTest {

    @Test
    void KeywordEntity로_Keyword를_만들_수_있다() {
        // given
        Long id = 1L;
        String keyword = "카카오";
        Long count = 1L;
        int version = 0;
        KeywordEntity target = KeywordEntity.builder()
                .keywordId(id)
                .keyword(keyword)
                .count(count)
                .version(version)
                .build();

        // when
        Keyword result = KeywordFactory.create(target);

        // then
        assertThat(result.getKeywordId()).isEqualTo(id);
        assertThat(result.getKeyword()).isEqualTo(keyword);
        assertThat(result.getCount()).isEqualTo(count);
        assertThat(result.getVersion()).isEqualTo(version);
    }

    @Test
    void keyword와_countHolder로_Keyword를_만들_수_있다() {
        // given
        String keyword = "카카오";
        CountHolder countHolder = new FakeCountHolder();

        // when
        Keyword result = KeywordFactory.create(keyword, countHolder);

        // then
        assertThat(result.getKeyword()).isEqualTo(keyword);
        assertThat(result.getCount()).isEqualTo(countHolder.one());
    }

}