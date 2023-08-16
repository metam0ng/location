package com.location.api.server.keyword.service.command;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.repository.command.KeywordCommandRepository;
import com.location.api.server.testsupport.repository.FakeKeywordCommandRepository;
import com.location.api.server.testsupport.service.TestCountHolder;
import com.location.common.holder.CountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordCommandServiceTest {

    private CountHolder countHolder;
    private KeywordCommandService keywordCommandService;
    private KeywordCommandRepository keywordCommandRepository;

    @BeforeEach
    void setUp() {
        countHolder = new TestCountHolder();
        keywordCommandRepository = new FakeKeywordCommandRepository();
        keywordCommandService = new KeywordCommandService(countHolder, keywordCommandRepository);
        keywordCommandRepository.save(Keyword.builder()
                    .keywordId(1L)
                    .keyword("카카오 뱅크")
                    .count(1L)
                    .version(0)
                    .build());
    }

    @Test
    void keyword로_조회되지_않는_대상을_생성_한다() {
        // given
        String keyword = "카카오";

        // when
        Keyword target = keywordCommandService.increaseCount(keyword);

        // then
        assertThat(target.getCount()).isEqualTo(countHolder.one());
    }

    @Test
    void keyword로_조회되는_대상의_count를_1_증가시킨다() {
        // given
        String keyword = "카카오 뱅크";

        // when
        Keyword target = keywordCommandService.increaseCount(keyword);

        // then
        assertThat(target.getKeyword()).isEqualTo(keyword);
        assertThat(target.getCount()).isEqualTo(2);
    }
}