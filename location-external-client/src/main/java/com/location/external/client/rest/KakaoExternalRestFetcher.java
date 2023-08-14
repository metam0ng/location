package com.location.external.client.rest;

import com.location.common.annotation.RetryAfterThrowException;
import com.location.external.client.rest.api.KakaoExternalApi;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.LocationInformations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoExternalRestFetcher implements LocationExternalClientFetcher {

    private final KakaoExternalApi kakaoExternalApi;

    @Override
    public boolean isSupport(ExternalType externalType) {
        return ExternalType.KAKAO == externalType;
    }

    @Override
    @RetryAfterThrowException
    public LocationInformations searchLocationByKeyword(String keyword,
                                                        int pageSize,
                                                        int totalSize) {
        List<KakaoLocationResponse> responses = new ArrayList<>();
        for (int pageNumber = 1; ; pageNumber++) {
            KakaoLocationResponse response = kakaoExternalApi.searchByKeword(keyword, pageNumber, pageSize);
            responses.add(response);
            if (response.isEnd(totalSize)) {
                break;
            }
        }
        return LocationInformations.fromKakao(responses);
    }


}
