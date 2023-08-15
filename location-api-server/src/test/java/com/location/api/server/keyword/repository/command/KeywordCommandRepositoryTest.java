package com.location.api.server.keyword.repository.command;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.repository.command.KeywordCommandRepository;
import com.location.api.server.keyword.repository.command.KeywordCommandRepositoryImpl;
import com.location.api.server.testsupport.repository.FakeSearchKeywordJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordCommandRepositoryTest {

    private KeywordCommandRepository keywordCommandRepository;

    @BeforeEach
    void setUp() {
        keywordCommandRepository = new KeywordCommandRepositoryImpl(new FakeSearchKeywordJPARepository());
    }

    @Test
    void keyword_를_save_할_수_있다() {
        // given
        String keyword = "카카오";
        Long count = 1L;
        int version = 0;
        Keyword target = Keyword.builder()
                .keyword(keyword)
                .count(count)
                .version(version)
                .build();

        // when
        Keyword result = keywordCommandRepository.save(target);

        // then
        assertThat(result.getKeyword()).isEqualTo(keyword);
        assertThat(result.getCount()).isEqualTo(count);
        assertThat(result.getVersion()).isEqualTo(version);
    }

    @Test
    void keyowrd로_조회_할_수_있다() {
        // given
        String keyword = "카카오";

        // when
        Optional<Keyword> result = keywordCommandRepository.findByKeyword(keyword);

        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getKeyword()).isEqualTo(keyword);
    }

}