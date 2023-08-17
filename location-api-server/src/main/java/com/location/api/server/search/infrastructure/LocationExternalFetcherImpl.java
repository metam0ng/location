package com.location.api.server.search.infrastructure;

import com.location.api.server.search.domain.LocationInformation;
import com.location.api.server.search.infrastructure.code.ExternalType;
import com.location.common.exception.LocationExternalApiNotFoundException;
import com.location.common.holder.ExceptionCountHolder;
import com.location.external.client.spec.LocationExternalClientFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationExternalFetcherImpl implements LocationExternalFetcher{

    private final List<LocationExternalClientFetcher> externalClientFetchers;


    @Override
    public LocationInformation searchLocationByKeyword(ExternalType externalType,
                                                       String keyword,
                                                       ExceptionCountHolder exceptionCountHolder) {
        try {
            LocationExternalClientFetcher locationExternalClientFetcher = externalClientFetchers.stream()
                    .filter(fetcher -> fetcher.isSupport(externalType.getApiType()))
                    .findFirst()
                    .orElseThrow(LocationExternalApiNotFoundException::new);
            return LocationInformation.from(locationExternalClientFetcher.searchLocationByKeyword(keyword, externalType.getPageSize(),
                    externalType.getTotalSize()));
        } catch (Exception e) {
            exceptionCountHolder.increase();
            return new LocationInformation(new ArrayList<>());
        }

    }
}
