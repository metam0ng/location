package com.location.api.server.keyword.repository.command;

import com.location.api.server.keyword.domain.Keyword;

import java.util.Optional;

public interface KeywordCommandRepository {
    Keyword save(Keyword keyword);

    Optional<Keyword> findByKeyword(String keyword);
}
