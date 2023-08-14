package com.location.api.server.infrastructure;

import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.LocationInformations;

public interface LocationExternalFetcher {

    LocationInformations searchLocationByKeyword(ExternalType externalType,
                                                 String keyword,
                                                 int pageSize,
                                                 int totalSize);
}
