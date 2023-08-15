package com.location.external.client.rest.api;

import com.location.external.client.config.LocationExternalClientProperties;
import com.location.external.client.config.LocationExternalRestTemplateProvider;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class NaverExternalApiTest {

    private NaverExternalApi naverExternalApi;

    @BeforeEach
    void setUp() {
        LocationExternalClientProperties properties = new LocationExternalClientProperties();
        properties.setKakaoUrl("https://dapi.kakao.com");
        properties.setNaverUrl("https://openapi.naver.com");
        properties.setConnectTimeout(Duration.ofSeconds(2));
        properties.setReadTimeout(Duration.ofSeconds(5));
        LocationExternalRestTemplateProvider restTemplateProvider = new LocationExternalRestTemplateProvider(properties, null);
        naverExternalApi = new NaverExternalApi(restTemplateProvider.getNaverMapRestTemplate(), properties);
    }


    @Test
    @Disabled
    void 네이버_지도를_키워드로_검색_할_수_있다() {
        // given
        String keyword = "카카오 프렌즈";
        int pageNumber = 2;
        int size = 10;

        // when
        NaverLocationResponse result = naverExternalApi.searchByKeword(keyword, pageNumber, size);

        // then
        assertThat(result).isNotNull();
    }

}