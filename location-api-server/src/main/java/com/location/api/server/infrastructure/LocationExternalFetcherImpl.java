package com.location.api.server.infrastructure;

import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.LocationInformations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationExternalFetcherImpl implements LocationExternalFetcher{

    private final List<LocationExternalClientFetcher> externalClientFetchers;


    @Override
    public LocationInformations searchLocationByKeyword(ExternalType externalType,
                                                        String keyword,
                                                        int pageSize,
                                                        int totalSize) {
        // todo. 하드코딩 enum으로 대체, externalType도 다른 enum으로 교체, locationInformations 교체
        LocationExternalClientFetcher locationExternalClientFetcher = externalClientFetchers.stream().filter(fetcher -> fetcher.isSupport(externalType)).findFirst().get();
        return locationExternalClientFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);
    }
}
