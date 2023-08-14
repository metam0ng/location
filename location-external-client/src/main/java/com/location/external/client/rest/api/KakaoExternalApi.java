package com.location.external.client.rest.api;

import com.location.external.client.config.LocationExternalRestTemplateProvider;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class KakaoExternalApi {

    private final RestTemplate restTemplate;

    public KakaoExternalApi(LocationExternalRestTemplateProvider restTemplateProvider) {
        restTemplate = restTemplateProvider.getKakaoMapRestTemplate();
    }

    public KakaoLocationResponse searchByKeword(String keyword,
                                                int pageNumber,
                                                int size) {
        // https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword
        final String url = UriComponentsBuilder.fromPath("/v2/local/search/keyword.json")
                .queryParam("query", keyword)
                .queryParam("size", size)
                .queryParam("page", pageNumber)
                .build()
                .toUriString();
        return restTemplate.getForObject(url, KakaoLocationResponse.class);
    }

}
