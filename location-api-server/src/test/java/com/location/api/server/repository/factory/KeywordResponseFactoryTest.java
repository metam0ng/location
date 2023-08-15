package com.location.api.server.repository.factory;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.repository.dto.KeywordDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordResponseFactoryTest {

    @Test
    void keywordDto_list_로_keywordRespons_list를_만들_수_있다() {
        // given
        KeywordDto keywordDto1 = KeywordDto.builder()
                .keyword("카카오")
                .count(10L)
                .build();

        KeywordDto keywordDto2 = KeywordDto.builder()
                .keyword("카카오 뱅크")
                .count(5L)
                .build();
        List<KeywordDto> dtos = List.of(keywordDto1, keywordDto2);

        // when
        List<KeywordResponse> result = KeywordResponseFactory.create(dtos);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getKeyword()).isEqualTo("카카오");
        assertThat(result.get(0).getCount()).isEqualTo(10L);
        assertThat(result.get(1).getKeyword()).isEqualTo("카카오 뱅크");
        assertThat(result.get(1).getCount()).isEqualTo(5L);
    }

}