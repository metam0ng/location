package com.location.api.server.keyword.service.query;

import com.location.api.server.keyword.dto.response.KeywordResponse;
import com.location.api.server.keyword.repository.query.KeywordQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordQueryService {

    private final KeywordQueryRepository keywordQueryRepository;
    public List<KeywordResponse> findKeywordTop10() {
        return keywordQueryRepository.findKeywordTop10();
    }
}
