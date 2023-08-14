package com.location.api.server.service.query;

import com.location.api.server.event.SearchEvent;
import com.location.api.server.infrastructure.LocationExternalFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.LocationInformations;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SearchQueryService {

    private final LocationExternalFetcher locationExternalFetcher;
    private final ApplicationEventPublisher applicationEventPublisher;
    private static final int TOTAL_SIZE = 10;
    private static final int KAKAO_SIZE = 10;
    private static final int NAVER_SIZE = 5;


    public List<String> searchLocationByKeyword(String keyword) {
        LocationInformations kakaoLocationInformations = locationExternalFetcher.searchLocationByKeyword(ExternalType.KAKAO, keyword, KAKAO_SIZE, TOTAL_SIZE);
        LocationInformations naverLocationInformations = locationExternalFetcher.searchLocationByKeyword(ExternalType.NAVER, keyword, NAVER_SIZE, TOTAL_SIZE);
        applicationEventPublisher.publishEvent(new SearchEvent(this, keyword));
        return mergeResults(kakaoLocationInformations, naverLocationInformations);
    }

    private List<String> mergeResults(LocationInformations kakaoLocationInformations,
                                      LocationInformations naverLocationInformations) {
        List<String> result = new ArrayList<>();
        // 카카오 결과에 기반하여 먼저 추가
        result.addAll(kakaoLocationInformations.findSameLocationAndRemove(naverLocationInformations));
        // 카카오에만 있는 결과 추가
        result.addAll(kakaoLocationInformations.findLocationName(10 - result.size() - naverLocationInformations.size()));
        // 네이버에만 있는 결과 추가
        result.addAll(naverLocationInformations.findLocationName());
        // 나머지 부족한 10개 맞추기
        result.addAll(kakaoLocationInformations.findLocationName(10 - result.size()));

        return result;
    }
}
