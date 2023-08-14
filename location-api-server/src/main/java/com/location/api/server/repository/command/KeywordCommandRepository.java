package com.location.api.server.repository.command;

import com.location.api.server.domain.Keyword;

import java.util.Optional;

public interface KeywordCommandRepository {
    Keyword save(Keyword keyword);

    Optional<Keyword> findByKeyword(String keyword);
}
