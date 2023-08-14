package com.location.external.client.stub;

import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.Coordinate;
import com.location.external.client.spec.dto.LocationInformation;
import com.location.external.client.spec.dto.LocationInformations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoExternalStubFetcher implements LocationExternalClientFetcher {

    @Override
    public boolean isSupport(ExternalType externalType) {
        return ExternalType.KAKAO == externalType;
    }

    @Override
    public LocationInformations searchLocationByKeyword(String keyword,
                                                        int pageSize,
                                                        int totalSize) {
        return LocationInformations.from(List.of(LocationInformation.builder()
                .name("카카오프렌즈 코엑스점")
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .build()));
    }


}
