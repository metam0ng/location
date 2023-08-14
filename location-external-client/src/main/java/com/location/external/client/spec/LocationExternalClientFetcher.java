package com.location.external.client.spec;

import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.LocationInformations;

public interface LocationExternalClientFetcher {

    boolean isSupport(ExternalType externalType);

    LocationInformations searchLocationByKeyword(String keyword,
                                                 int pageSize,
                                                 int totalSize);

}
