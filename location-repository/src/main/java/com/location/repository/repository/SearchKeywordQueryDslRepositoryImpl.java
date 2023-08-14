package com.location.repository.repository;

import com.location.repository.dto.KeywordDto;
import com.location.repository.dto.QKeywordDto;
import com.location.repository.entity.KeywordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.location.repository.entity.QKeywordEntity.keywordEntity;


@Repository
public class SearchKeywordQueryDslRepositoryImpl extends QuerydslRepositorySupport implements SearchKeywordQueryDslRepository{

    public SearchKeywordQueryDslRepositoryImpl() {
        super(KeywordEntity.class);
    }

    @Override
    public List<KeywordDto> findKeywordTop10() {
        return getQuerydsl().createQuery()
                .select(new QKeywordDto(
                        keywordEntity.keyword,
                        keywordEntity.count))
                .from(keywordEntity)
                .orderBy(keywordEntity.count.desc())
                .limit(10)
                .fetch();
    }
}
