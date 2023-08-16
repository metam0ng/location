package com.location.api.server.search.service.query;

import com.location.api.server.common.event.SearchEvent;
import com.location.api.server.search.domain.LocationInformation;
import com.location.api.server.search.dto.response.SearchResponse;
import com.location.api.server.search.infrastructure.LocationExternalFetcher;
import com.location.api.server.search.infrastructure.code.ExternalType;
import com.location.common.exception.LocationExternalApiException;
import com.location.common.holder.CooridinateErrorRangeHolder;
import com.location.common.holder.ExceptionCountHolder;
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

    private final ExceptionCountHolder exceptionCountHolder;
    private final LocationExternalFetcher locationExternalFetcher;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CooridinateErrorRangeHolder cooridinateErrorRangeHolder;


    public List<SearchResponse> searchLocationByKeyword(String keyword) {
        String stripKeyword = keyword.strip();
        LocationInformation kakaoLocationInformation = locationExternalFetcher.searchLocationByKeyword(ExternalType.KAKAO, stripKeyword, exceptionCountHolder);
        LocationInformation naverLocationInformation = locationExternalFetcher.searchLocationByKeyword(ExternalType.NAVER, stripKeyword, exceptionCountHolder);
        if (exceptionCountHolder.isEqualOrOver(2)) throw new LocationExternalApiException();
        List<SearchResponse> results = mergeResults(kakaoLocationInformation, naverLocationInformation);
        applicationEventPublisher.publishEvent(new SearchEvent(this, stripKeyword));
        return results;
    }


    private List<SearchResponse> mergeResults(LocationInformation kakaoLocationInformation,
                                              LocationInformation naverLocationInformation) {
        List<SearchResponse> result = new ArrayList<>();
        // 카카오 결과에 기반하여 먼저 추가
        result.addAll(kakaoLocationInformation.findCountLocationAndRemove(naverLocationInformation, cooridinateErrorRangeHolder));
        // 카카오에만 있는 결과 추가
        int findKakaoCount = 10 - result.size() - naverLocationInformation.size();
        result.addAll(kakaoLocationInformation.findCountLocationAndRemove(findKakaoCount));
        // 네이버에만 있는 결과 추가
        result.addAll(naverLocationInformation.findLocation());
        return result;
    }
}
