package com.location.api.server.keyword.repository.factory;

import com.location.api.server.keyword.dto.response.KeywordResponse;
import com.location.repository.dto.KeywordDto;

import java.util.List;

public class KeywordResponseFactory {
    private KeywordResponseFactory() {
    }

    public static List<KeywordResponse> create(List<KeywordDto> dtos) {
        return dtos.stream()
                .map(KeywordResponseFactory::build)
                .toList();
    }

    private static KeywordResponse build(KeywordDto keywordDto) {
        return KeywordResponse.builder()
                .keyword(keywordDto.getKeyword())
                .count(keywordDto.getCount())
                .build();
    }
}
