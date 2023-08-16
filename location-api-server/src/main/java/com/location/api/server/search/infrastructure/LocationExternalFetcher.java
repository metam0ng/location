package com.location.api.server.search.infrastructure;

import com.location.api.server.search.domain.LocationInformation;
import com.location.api.server.search.infrastructure.code.ExternalType;
import com.location.common.holder.ExceptionCountHolder;

public interface LocationExternalFetcher {

    LocationInformation searchLocationByKeyword(ExternalType externalType,
                                                String keyword,
                                                ExceptionCountHolder exceptionCountHolder);
}
