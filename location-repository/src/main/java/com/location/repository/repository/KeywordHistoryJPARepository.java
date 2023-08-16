package com.location.repository.repository;

import com.location.repository.entity.KeywordHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordHistoryJPARepository extends JpaRepository<KeywordHistoryEntity, Long> {
}
