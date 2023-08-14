package com.location.repository.repository;

import com.location.repository.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchKeywordJPARepository extends JpaRepository<KeywordEntity, Long> {
    Optional<KeywordEntity> findByKeyword(String keyword);
}
