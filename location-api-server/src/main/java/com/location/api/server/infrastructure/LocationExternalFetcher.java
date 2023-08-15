package com.location.api.server.infrastructure;

import com.location.api.server.domain.LocationInformation;
import com.location.api.server.infrastructure.code.ExternalType;

public interface LocationExternalFetcher {

    LocationInformation searchLocationByKeyword(ExternalType externalType,
                                                String keyword,
                                                int pageSize,
                                                int totalSize);
}
