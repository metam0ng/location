package com.location.api.server.service.query;

import com.location.api.server.domain.LocationInformation;
import com.location.api.server.event.SearchEvent;
import com.location.api.server.infrastructure.LocationExternalFetcher;
import com.location.api.server.infrastructure.code.ExternalType;
import com.location.common.holder.ErrorRangeHolder;
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

    private final ErrorRangeHolder errorRangeHolder;
    private final LocationExternalFetcher locationExternalFetcher;
    private final ApplicationEventPublisher applicationEventPublisher;
    private static final int TOTAL_SIZE = 10;
    private static final int KAKAO_SIZE = 10;
    private static final int NAVER_SIZE = 5;


    public List<String> searchLocationByKeyword(String keyword) {
        String stripKeyword = keyword.strip();
        LocationInformation kakaoLocationInformation = locationExternalFetcher.searchLocationByKeyword(ExternalType.KAKAO, stripKeyword, KAKAO_SIZE, TOTAL_SIZE);
        LocationInformation naverLocationInformation = locationExternalFetcher.searchLocationByKeyword(ExternalType.NAVER, stripKeyword, NAVER_SIZE, TOTAL_SIZE);
        List<String> results = mergeResults(kakaoLocationInformation, naverLocationInformation);
        applicationEventPublisher.publishEvent(new SearchEvent(this, stripKeyword));
        return results;
    }

    private List<String> mergeResults(LocationInformation kakaoLocationInformation,
                                      LocationInformation naverLocationInformation) {
        List<String> result = new ArrayList<>();
        // 카카오 결과에 기반하여 먼저 추가
        result.addAll(kakaoLocationInformation.findSameLocationAndRemove(naverLocationInformation, errorRangeHolder));
        // 카카오에만 있는 결과 추가
        int findKakaoCount = 10 - result.size() - naverLocationInformation.size();
        result.addAll(kakaoLocationInformation.findLocationName(findKakaoCount));
        // 네이버에만 있는 결과 추가
        result.addAll(naverLocationInformation.findLocationName());
        // 나머지 부족한 10개 맞추기
        int leftCount = 10 - result.size();
        result.addAll(kakaoLocationInformation.findLocationName(leftCount));

        return result;
    }
}
