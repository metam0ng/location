package com.location.api.server.repository.query;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.repository.factory.KeywordResponseFactory;
import com.location.repository.repository.SearchKeywordQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class KeywordQueryRepositoryImpl implements KeywordQueryRepository {

    private final SearchKeywordQueryDslRepository searchKeywordQueryDslRepository;

    @Override
    public List<KeywordResponse> findKeywordTop10() {
        return KeywordResponseFactory.create(searchKeywordQueryDslRepository.findKeywordTop10());
    }
}
