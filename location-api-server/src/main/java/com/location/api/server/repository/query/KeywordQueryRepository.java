package com.location.api.server.repository.query;

import com.location.api.server.dto.response.KeywordResponse;

import java.util.List;

public interface KeywordQueryRepository {
    List<KeywordResponse> findKeywordTop10();
}
