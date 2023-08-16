package com.location.api.server.keyword.repository.command;

import com.location.api.server.keyword.domain.Keyword;
import com.location.api.server.keyword.repository.factory.KeywordEntityFactory;
import com.location.api.server.keyword.repository.factory.KeywordFactory;
import com.location.repository.entity.KeywordEntity;
import com.location.repository.repository.KeywordJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
public class KeywordCommandRepositoryImpl extends QuerydslRepositorySupport implements KeywordCommandRepository {

    private final KeywordJPARepository keywordJPARepository;

    public KeywordCommandRepositoryImpl(KeywordJPARepository keywordJPARepository) {
        super(KeywordEntity.class);
        this.keywordJPARepository = keywordJPARepository;
    }

    @Override
    public Keyword save(Keyword keyword) {
        return KeywordFactory.create(keywordJPARepository.save(KeywordEntityFactory.create(keyword)));
    }

    @Override
    public Optional<Keyword> findByKeyword(String keyword) {
        return keywordJPARepository.findByKeyword(keyword).map(KeywordFactory::create);
    }
}
