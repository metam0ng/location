package com.location.api.server.search.infrastructure;

import com.location.api.server.search.domain.LocationInformation;
import com.location.api.server.search.infrastructure.code.ExternalType;

public interface LocationExternalFetcher {

    LocationInformation searchLocationByKeyword(ExternalType externalType,
                                                String keyword,
                                                int pageSize,
                                                int totalSize);
}
