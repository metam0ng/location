package com.location.external.client.spec;

import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.LocationClientResponse;

import java.util.List;

public interface LocationExternalClientFetcher {

    boolean isSupport(ApiType apiType);

    List<LocationClientResponse> searchLocationByKeyword(String keyword,
                                                         int pageSize,
                                                         int totalSize);

}
