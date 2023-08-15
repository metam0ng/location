package com.location.external.client.rest;

import com.location.external.client.rest.api.KakaoExternalApi;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.LocationClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoExternalRestFetcher implements LocationExternalClientFetcher {

    private final KakaoExternalApi kakaoExternalApi;

    @Override
    public boolean isSupport(ApiType apiType) {
        return ApiType.KAKAO == apiType;
    }

    @Override
    public List<LocationClientResponse> searchLocationByKeyword(String keyword,
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
        return responses.stream()
                .map(res -> res.getDocuments().stream()
                        .map(LocationClientResponse::fromKakao)
                        .toList())
                .flatMap(List::stream)
                .toList();
    }


}
