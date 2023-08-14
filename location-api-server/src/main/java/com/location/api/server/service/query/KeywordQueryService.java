package com.location.api.server.service.query;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.repository.query.KeywordQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordQueryService {

    private final KeywordQueryRepository keywordQueryRepository;
    public List<KeywordResponse> findKeywords() {
        return keywordQueryRepository.findKeywordTop10();
    }
}
