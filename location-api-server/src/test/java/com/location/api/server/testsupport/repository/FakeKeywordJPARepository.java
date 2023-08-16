package com.location.api.server.testsupport.repository;

import com.location.repository.entity.KeywordEntity;
import com.location.repository.repository.KeywordJPARepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FakeKeywordJPARepository implements KeywordJPARepository {
    @Override
    public Optional<KeywordEntity> findByKeyword(String keyword) {
        if (keyword.contains("카카오")) {
            return Optional.of(KeywordEntity.builder()
                    .keywordId(1L)
                    .keyword(keyword)
                    .count(1L)
                    .version(0)
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public List<KeywordEntity> findAll() {
        return null;
    }

    @Override
    public List<KeywordEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<KeywordEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<KeywordEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(KeywordEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends KeywordEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends KeywordEntity> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends KeywordEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<KeywordEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends KeywordEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends KeywordEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<KeywordEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public KeywordEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public KeywordEntity getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends KeywordEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends KeywordEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends KeywordEntity> List<S> findAll(Example<S> example,
                                                     Sort sort) {
        return null;
    }

    @Override
    public <S extends KeywordEntity> Page<S> findAll(Example<S> example,
                                                     Pageable pageable) {
        return null;
    }

    @Override
    public <S extends KeywordEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends KeywordEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends KeywordEntity, R> R findBy(Example<S> example,
                                                 Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
