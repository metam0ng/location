package com.location.repository.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Entity
@Table(name = "keyword_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class KeywordHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordHistoryId;

    @Column(name = "keyword", nullable = false, columnDefinition = "varchar(20) COMMENT '검색 키워드'")
    private String keyword;

    @CreatedDate
    @Column(name = "created_datetime", nullable = false, columnDefinition = "datetime(3) COMMENT '등록일시'")
    private LocalDateTime createdDatetime;

    @Builder
    public KeywordHistoryEntity(Long keywordHistoryId,
                                String keyword,
                                LocalDateTime createdDatetime) {
        this.keywordHistoryId = keywordHistoryId;
        this.keyword = keyword;
        this.createdDatetime = createdDatetime;
    }
}
