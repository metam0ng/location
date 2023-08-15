package com.location.external.client.rest.api;

import com.location.external.client.config.LocationExternalClientProperties;
import com.location.external.client.config.LocationExternalRestTemplateProvider;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoExternalApiTest {

    private KakaoExternalApi kakaoExternalApi;

    @BeforeEach
    void setUp() {
        LocationExternalClientProperties properties = new LocationExternalClientProperties();
        properties.setKakaoUrl("https://dapi.kakao.com");
        properties.setNaverUrl("https://openapi.naver.com");
        properties.setConnectTimeout(Duration.ofSeconds(2));
        properties.setReadTimeout(Duration.ofSeconds(5));
        LocationExternalRestTemplateProvider restTemplateProvider = new LocationExternalRestTemplateProvider(properties, null);
        kakaoExternalApi = new KakaoExternalApi(restTemplateProvider);
    }


    @Test
    @Disabled
    void 카카오_지도를_키워드로_검색_할_수_있다() {
        // given
        String keyword = "카카오";
        int size = 10;
        int pageNumber = 1;

        // when
        KakaoLocationResponse result = kakaoExternalApi.searchByKeword(keyword, pageNumber, size);

        // then
        assertThat(result.getKaKaoLocationMeta().getKaKaoLocationSameName().getKeyword()).isEqualTo(keyword);
        assertThat(result.getDocuments().size()).isEqualTo(size);
    }

}