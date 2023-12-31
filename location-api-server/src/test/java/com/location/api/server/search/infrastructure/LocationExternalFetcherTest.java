package com.location.api.server.search.infrastructure;

import com.location.api.server.search.domain.LocationInformation;
import com.location.api.server.search.infrastructure.code.ExternalType;
import com.location.api.server.testsupport.service.FakeLocationExternalClientKakaoFetcher;
import com.location.api.server.testsupport.service.FakeLocationExternalClientNaverFetcher;
import com.location.api.server.testsupport.service.TestExceptionCountHolder;
import com.location.common.holder.ExceptionCountHolder;
import com.location.external.client.spec.LocationExternalClientFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationExternalFetcherTest {

    private LocationExternalFetcher locationExternalFetcher;


    @BeforeEach
    void setUp() {
        FakeLocationExternalClientKakaoFetcher fakeLocationExternalClientKakaoFetcher = new FakeLocationExternalClientKakaoFetcher();
        FakeLocationExternalClientNaverFetcher fakeLocationExternalClientNaverFetcher = new FakeLocationExternalClientNaverFetcher();
        List<LocationExternalClientFetcher> locationExternalClientFetcher = List.of(fakeLocationExternalClientKakaoFetcher, fakeLocationExternalClientNaverFetcher);
        locationExternalFetcher = new LocationExternalFetcherImpl(locationExternalClientFetcher);
    }

    @Test
    void keyword를_통해_검색_결과를_조회_할_수_있다() {
        // give
        ExceptionCountHolder exceptionCountHolder = new TestExceptionCountHolder(0);

        // when
        LocationInformation result = locationExternalFetcher.searchLocationByKeyword(ExternalType.KAKAO, "카카오", exceptionCountHolder);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 일치하는_ExternalType이_없다면_Exception_count가_증가한다() {
        // given
        ExternalType externalType = null;
        String keyword = "카카오";
        TestExceptionCountHolder exceptionCountHolder = new TestExceptionCountHolder(0);

        // when
        LocationInformation result = locationExternalFetcher.searchLocationByKeyword(externalType, keyword, exceptionCountHolder);

        // then
        assertThat(exceptionCountHolder.getCount()).isEqualTo(1);
    }

}