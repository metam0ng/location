package com.location.external.client.rest;

import com.location.common.annotation.RetryAfterThrowException;
import com.location.external.client.rest.api.NaverExternalApi;
import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.LocationClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverExternalRestFetcher implements LocationExternalClientFetcher {

    private final NaverExternalApi naverExternalApi;
    private final CoordinateConverter coordinateConverter;

    @Override
    public boolean isSupport(ApiType apiType) {
        return ApiType.NAVER == apiType;
    }

    @Override
    @RetryAfterThrowException
    public List<LocationClientResponse> searchLocationByKeyword(String keyword,
                                                                int pageSize,
                                                                int totalSize) {
        NaverLocationResponse response = naverExternalApi.searchByKeword(keyword, 1, totalSize);
        return response.getNaverLocationItems().stream()
                .map(dto -> LocationClientResponse.fromNaver(dto, coordinateConverter))
                .toList();
    }

}
