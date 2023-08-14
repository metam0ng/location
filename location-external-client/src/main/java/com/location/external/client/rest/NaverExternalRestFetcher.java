package com.location.external.client.rest;

import com.location.common.annotation.RetryAfterThrowException;
import com.location.external.client.rest.api.NaverExternalApi;
import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.spec.dto.LocationInformations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverExternalRestFetcher implements LocationExternalClientFetcher {

    private final NaverExternalApi naverExternalApi;
    private final CoordinateConverter coordinateConverter;

    @Override
    public boolean isSupport(ExternalType externalType) {
        return ExternalType.NAVER == externalType;
    }

    @Override
    @RetryAfterThrowException
    public LocationInformations searchLocationByKeyword(String keyword,
                                                        int pageSize,
                                                        int totalSize) {
        return LocationInformations.fromNaver(naverExternalApi.searchByKeword(keyword, 1, totalSize), coordinateConverter);
    }

}
