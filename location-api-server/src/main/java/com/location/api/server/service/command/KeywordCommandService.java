package com.location.api.server.service.command;

import com.location.api.server.domain.Keyword;
import com.location.api.server.repository.command.KeywordCommandRepository;
import com.location.api.server.repository.factory.KeywordFactory;
import com.location.common.annotation.Retry;
import com.location.common.holder.CountHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordCommandService {

    private final CountHolder countHolder;
    private final KeywordCommandRepository keywordCommandRepository;

    @Transactional
    @Retry
    public Keyword increaseCount(String keyword) {
        Keyword targetKeyword = keywordCommandRepository.findByKeyword(keyword)
                .map(domain -> domain.increaseCount(countHolder))
                .orElse(KeywordFactory.create(keyword, countHolder));
        return keywordCommandRepository.save(targetKeyword);
    }
}
