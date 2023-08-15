package com.location.api.server.keyword.domain;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.testsupport.service.FakeCountHolder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KeywordTest {

    @Test
    void increaseCount를_호출하면_count가_증가한다() {
        // given
        Keyword keyword = Keyword.builder()
                .keyword("카카오")
                .count(1L)
                .version(1)
                .build();

        // when
        keyword.increaseCount(new FakeCountHolder());

        // then
        assertThat(keyword.getCount()).isEqualTo(2);
    }

}
