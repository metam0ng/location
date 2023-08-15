package com.location.api.server.keyword.repository.query;

import com.location.api.server.keyword.dto.response.KeywordResponse;

import java.util.List;

public interface KeywordQueryRepository {
    List<KeywordResponse> findKeywordTop10();
}
