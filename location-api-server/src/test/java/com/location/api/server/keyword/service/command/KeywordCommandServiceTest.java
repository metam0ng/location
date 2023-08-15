package com.location.api.server.keyword.service.command;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.service.command.KeywordCommandService;
import com.location.api.server.keyword.repository.command.KeywordCommandRepository;
import com.location.api.server.testsupport.repository.FakeKeywordCommandRepository;
import com.location.api.server.testsupport.service.FakeCountHolder;
import com.location.common.holder.CountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordCommandServiceTest {

    private KeywordCommandService keywordCommandService;
    private CountHolder countHolder;
    private KeywordCommandRepository keywordCommandRepository;

    @BeforeEach
    void setUp() {
        countHolder = new FakeCountHolder();
        keywordCommandRepository = new FakeKeywordCommandRepository();
        keywordCommandService = new KeywordCommandService(countHolder, keywordCommandRepository);
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
        Keyword fake = keywordCommandRepository.findByKeyword(keyword).get();
        assertThat(target.getKeyword()).isEqualTo(keyword);
        assertThat(target.getCount()).isEqualTo(fake.getCount() + countHolder.one());

    }
}