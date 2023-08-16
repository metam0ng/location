package com.location.api.server.keyword.repository.query;

import com.location.api.server.keyword.dto.response.KeywordResponse;
import com.location.api.server.keyword.repository.factory.KeywordResponseFactory;
import com.location.repository.repository.KeywordQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class KeywordQueryRepositoryImpl implements KeywordQueryRepository {

    private final KeywordQueryDslRepository keywordQueryDslRepository;

    @Override
    public List<KeywordResponse> findKeywordTop10() {
        return KeywordResponseFactory.create(keywordQueryDslRepository.findKeywordTop10());
    }
}
