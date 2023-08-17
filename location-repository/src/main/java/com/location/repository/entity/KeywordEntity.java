package com.location.repository.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@Table(name = "keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @Column(name = "keyword", nullable = false, columnDefinition = "varchar(20) COMMENT '검색 키워드'", unique = true)
    private String keyword;

    @Column(name = "count", nullable = false, columnDefinition = "bigint COMMENT '검색 수'")
    private Long count;

    @Version
    private long version;

    @Builder
    public KeywordEntity(Long keywordId,
                         String keyword,
                         Long count,
                         long version) {
        this.keywordId = keywordId;
        this.keyword = keyword;
        this.count = count;
        this.version = version;
    }
}
