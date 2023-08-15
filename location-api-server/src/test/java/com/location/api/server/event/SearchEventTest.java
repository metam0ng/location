package com.location.api.server.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchEventTest {

    @Test
    void SearchEvent에서_keyword를_가지고_올_수_있다() {
        // given
        String keyword = "카카오";
        SearchEvent searchEvent = new SearchEvent(this, keyword);

        // when
        String ressut = searchEvent.getKeyword();

        // then
        assertThat(ressut).isEqualTo(keyword);
    }

}