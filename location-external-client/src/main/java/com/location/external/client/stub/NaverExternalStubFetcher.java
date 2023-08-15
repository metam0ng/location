package com.location.external.client.stub;

import com.location.common.annotation.RetryAfterThrowException;
import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.CoordinateDto;
import com.location.external.client.spec.dto.LocationClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverExternalStubFetcher implements LocationExternalClientFetcher {

    @Override
    public boolean isSupport(ApiType apiType) {
        return ApiType.NAVER == apiType;
    }

    @Override
    @RetryAfterThrowException
    public List<LocationClientResponse> searchLocationByKeyword(String userId,
                                                                int pageSize,
                                                                int totalSize) {

        return List.of(LocationClientResponse.builder()
                .name("카카오프렌즈 코엑스점")
                .coordinateDto(CoordinateDto.of(126.981682046205, 37.5635252637636))
                .build());
    }

}
