package com.location.external.client.rest.api;

import com.location.external.client.config.LocationExternalClientProperties;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class NaverExternalApi {

    private final RestTemplate restTemplate;
    private final String HOST;

    public NaverExternalApi(RestTemplate naverRestTemplate,
                            LocationExternalClientProperties properties) {
        restTemplate = naverRestTemplate;
        HOST = properties.getNaverUrl();
    }

    public NaverLocationResponse searchByKeword(String keyword,
                                                int pageNumber,
                                                int size) {
        // https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
        final String url = UriComponentsBuilder.fromHttpUrl(HOST + "/v1/search/local.json")
                .queryParam("query", keyword)
                .queryParam("start", pageNumber)
                .queryParam("display", size)
                .build()
                .toUriString();
        return restTemplate.getForObject(url, NaverLocationResponse.class);
    }

}
