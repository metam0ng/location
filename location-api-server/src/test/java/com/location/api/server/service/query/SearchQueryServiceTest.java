package com.location.api.server.service.query;

import com.location.api.server.domain.LocationInformation;
import com.location.api.server.infrastructure.LocationExternalFetcher;
import com.location.api.server.infrastructure.LocationExternalFetcherImpl;
import com.location.api.server.infrastructure.code.ExternalType;
import com.location.api.server.service.query.SearchQueryService;
import com.location.api.server.testsupport.service.FakeApplicationEventPublisher;
import com.location.api.server.testsupport.service.FakeErrorRangeHolder;
import com.location.api.server.testsupport.service.FakeLocationExternalClientKakaoFetcher;
import com.location.api.server.testsupport.service.FakeLocationExternalClientNaverFetcher;
import com.location.common.holder.ErrorRangeHolder;
import com.location.external.client.spec.LocationExternalClientFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchQueryServiceTest {

    private ErrorRangeHolder errorRangeHolder;
    private SearchQueryService searchQueryService;
    private LocationExternalFetcher locationExternalFetcher;


    @BeforeEach
    void setUp() {
        errorRangeHolder = new FakeErrorRangeHolder();
        FakeLocationExternalClientKakaoFetcher fakeLocationExternalClientKakaoFetcher = new FakeLocationExternalClientKakaoFetcher();
        FakeLocationExternalClientNaverFetcher fakeLocationExternalClientNaverFetcher = new FakeLocationExternalClientNaverFetcher();
        List<LocationExternalClientFetcher> locationExternalClientFetcher = List.of(fakeLocationExternalClientKakaoFetcher, fakeLocationExternalClientNaverFetcher);
        locationExternalFetcher = new LocationExternalFetcherImpl(locationExternalClientFetcher);
        ApplicationEventPublisher applicationEventPublisher = new FakeApplicationEventPublisher();
        searchQueryService = new SearchQueryService(errorRangeHolder, locationExternalFetcher, applicationEventPublisher);
    }

    @Test
    void keyword를_통해_검색_결과를_조회_할_수_있다() {
        // give
        String keyword = "카카오";

        // when
        List<String> result = searchQueryService.searchLocationByKeyword(keyword);

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    void 중복된_결과가_있다고_해도_10개의_결과를_조회_할_수_있다() {
        // give
        String keyword = "네이버";

        // when
        List<String> result = searchQueryService.searchLocationByKeyword(keyword);

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    void 카카오_검색_결과가_우선으로_조회_된다() {
        // give
        String keyword = "네이버";

        // when
        List<String> result = searchQueryService.searchLocationByKeyword(keyword);

        // then
        assertThat(result).isNotEmpty();
        LocationInformation locationInformation = locationExternalFetcher.searchLocationByKeyword(ExternalType.NAVER,"네이버", 5, 5);
        assertThat(result).isNotEmpty();
        assertThat(result.get(0)).isEqualTo(locationInformation.get(0).getName());

    }

    @Test
    void 네이버_검색_결과가_부족하면_카카오_검색_결과로_10개를_채운다() {
        // give
        String keyword = "카카오 프렌즈";

        // when
        List<String> result = searchQueryService.searchLocationByKeyword(keyword);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(10);
    }



}