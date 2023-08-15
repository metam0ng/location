package com.location.api.server.infrastructure;

import com.location.api.server.domain.LocationInformation;
import com.location.api.server.infrastructure.code.ExternalType;
import com.location.external.client.spec.LocationExternalClientFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationExternalFetcherImpl implements LocationExternalFetcher{

    private final List<LocationExternalClientFetcher> externalClientFetchers;


    @Override
    public LocationInformation searchLocationByKeyword(ExternalType externalType,
                                                       String keyword,
                                                       int pageSize,
                                                       int totalSize) {
        LocationExternalClientFetcher locationExternalClientFetcher = externalClientFetchers.stream().filter(fetcher -> fetcher.isSupport(externalType.getApiType())).findFirst().get();
        return LocationInformation.from(locationExternalClientFetcher.searchLocationByKeyword(keyword, pageSize, totalSize));
    }
}
