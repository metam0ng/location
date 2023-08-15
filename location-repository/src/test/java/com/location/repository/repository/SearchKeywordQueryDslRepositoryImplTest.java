package com.location.repository.repository;

import com.location.repository.config.RepositoryConfig;
import com.location.repository.dto.KeywordDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = RepositoryConfig.class)
@AutoConfigureDataJpa
@Sql(value = "/sql/insert-keyword.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SearchKeywordQueryDslRepositoryImplTest {

    @Autowired
    SearchKeywordQueryDslRepository searchKeywordQueryDslRepository;

    @Test
    void 검색어_count수가_많은_순으로_10개가_조회_된다() {
        // given
        // when
        List<KeywordDto> result = searchKeywordQueryDslRepository.findKeywordTop10();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(10);
        int max = result.stream().mapToInt(value -> Math.toIntExact(value.getCount())).max().getAsInt();
        assertThat(result.get(0).getCount().intValue()).isEqualTo(max);
    }
}