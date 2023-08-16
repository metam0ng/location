package com.location.repository.repository;

import com.location.repository.dto.KeywordDto;

import java.util.List;

public interface KeywordQueryDslRepository {
    List<KeywordDto> findKeywordTop10();
}
