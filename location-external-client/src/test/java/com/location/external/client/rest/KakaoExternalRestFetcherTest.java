package com.location.external.client.rest;

import com.location.external.client.rest.api.KakaoExternalApi;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationDocument;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationMeta;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationSameName;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.LocationClientResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KakaoExternalRestFetcherTest {

    @InjectMocks
    private KakaoExternalRestFetcher kakaoExternalRestFetcher;
    @Mock
    private KakaoExternalApi kakaoExternalApi;

    @Test
    void ExternalType의_KAKAO를_지원한다() {
        // given
        ApiType type = ApiType.KAKAO;

        // when
        boolean result = kakaoExternalRestFetcher.isSupport(type);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void ExternalType의_NAVER를_지원하지_않는다() {
        // given
        ApiType type = ApiType.NAVER;

        // when
        boolean result = kakaoExternalRestFetcher.isSupport(type);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 키워드로_카카오_장소를_검색_할_수_있다() {
        // given
        String keyword = "카카오프렌즈";
        String placeName = "카카오프렌즈 코엑스점";
        int pageSize = 5;
        int totalSize = 10;

        KaKaoLocationSameName kaKaoLocationSameName = KaKaoLocationSameName.builder()
                .region(new ArrayList<>())
                .keyword("카카오프렌즈")
                .selectedRegion("")
                .build();
        KaKaoLocationMeta kaKaoLocationMeta = KaKaoLocationMeta.builder()
                .kaKaoLocationSameName(kaKaoLocationSameName)
                .pageableCount(14)
                .totalCount(14)
                .isEnd(true)
                .build();
        KaKaoLocationDocument kaKaoLocationDocument = KaKaoLocationDocument.builder()
                .placeName(placeName)
                .distance("418")
                .placeUrl("http://place.map.kakao.com/26338954")
                .categoryName("가정,생활 > 문구,사무용품 > 디자인문구 > 카카오프렌즈")
                .addressName("서울 강남구 삼성동 159")
                .roadAddressName("서울 강남구 영동대로 513")
                .id("26338954")
                .phone("02-6002-1880")
                .categoryGroupCode("")
                .categoryGroupName("")
                .x("127.05902969025047")
                .y("37.51207412593136")
                .build();
        KakaoLocationResponse response = KakaoLocationResponse.builder()
                .kaKaoLocationMeta(kaKaoLocationMeta)
                .documents(List.of(kaKaoLocationDocument))
                .build();

        given(kakaoExternalApi.searchByKeword(any(), any(Integer.class), any(Integer.class))).willReturn(response);

        // when
        List<LocationClientResponse> result = kakaoExternalRestFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);

        // then
        assertThat(result.size()).isEqualTo(response.getDocuments().size());
        assertThat(result.get(0).getName()).isEqualTo(placeName);

    }

    @Test
    void pageSize가_5이고_totalSize가_5개일때_5개의_이름이_반환된다() {
        // given
        String keyword = "카카오프렌즈";
        String placeName = "카카오프렌즈 코엑스점";
        int pageSize = 5;
        int totalSize = 5;

        KaKaoLocationSameName kaKaoLocationSameName = KaKaoLocationSameName.builder()
                .region(new ArrayList<>())
                .keyword("카카오프렌즈")
                .selectedRegion("")
                .build();
        KaKaoLocationMeta kaKaoLocationMeta = KaKaoLocationMeta.builder()
                .kaKaoLocationSameName(kaKaoLocationSameName)
                .pageableCount(14)
                .totalCount(14)
                .isEnd(true)
                .build();
        KaKaoLocationDocument kaKaoLocationDocument = KaKaoLocationDocument.builder()
                .placeName(placeName)
                .distance("418")
                .placeUrl("http://place.map.kakao.com/26338954")
                .categoryName("가정,생활 > 문구,사무용품 > 디자인문구 > 카카오프렌즈")
                .addressName("서울 강남구 삼성동 159")
                .roadAddressName("서울 강남구 영동대로 513")
                .id("26338954")
                .phone("02-6002-1880")
                .categoryGroupCode("")
                .categoryGroupName("")
                .x("127.05902969025047")
                .y("37.51207412593136")
                .build();

        List<KaKaoLocationDocument> kaoLocationDocuments = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            kaoLocationDocuments.add(kaKaoLocationDocument);
        }
        KakaoLocationResponse firstResponse = KakaoLocationResponse.builder()
                .kaKaoLocationMeta(kaKaoLocationMeta)
                .documents(kaoLocationDocuments)
                .build();

        kaKaoLocationMeta = KaKaoLocationMeta.builder()
                .kaKaoLocationSameName(kaKaoLocationSameName)
                .pageableCount(14)
                .totalCount(14)
                .isEnd(true)
                .build();

        KakaoLocationResponse secondResponse = KakaoLocationResponse.builder()
                .kaKaoLocationMeta(kaKaoLocationMeta)
                .documents(kaoLocationDocuments)
                .build();

        given(kakaoExternalApi.searchByKeword(any(), any(Integer.class), any(Integer.class))).willReturn(firstResponse).willReturn(secondResponse);

        // when
        List<LocationClientResponse> result = kakaoExternalRestFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);

        // then
        assertThat(result.size()).isEqualTo(totalSize);
        assertThat(result.get(0).getName()).isEqualTo(placeName);

    }

    @Test
    void pageSize가_5이고_totalSize가_10개일때_10개의_이름이_반환된다() {
        // given
        String keyword = "카카오프렌즈";
        String placeName = "카카오프렌즈 코엑스점";
        int pageSize = 5;
        int totalSize = 10;

        KaKaoLocationSameName kaKaoLocationSameName = KaKaoLocationSameName.builder()
                .region(new ArrayList<>())
                .keyword("카카오프렌즈")
                .selectedRegion("")
                .build();
        KaKaoLocationMeta kaKaoLocationMeta = KaKaoLocationMeta.builder()
                .kaKaoLocationSameName(kaKaoLocationSameName)
                .pageableCount(14)
                .totalCount(14)
                .isEnd(false)
                .build();
        KaKaoLocationDocument kaKaoLocationDocument = KaKaoLocationDocument.builder()
                .placeName(placeName)
                .distance("418")
                .placeUrl("http://place.map.kakao.com/26338954")
                .categoryName("가정,생활 > 문구,사무용품 > 디자인문구 > 카카오프렌즈")
                .addressName("서울 강남구 삼성동 159")
                .roadAddressName("서울 강남구 영동대로 513")
                .id("26338954")
                .phone("02-6002-1880")
                .categoryGroupCode("")
                .categoryGroupName("")
                .x("127.05902969025047")
                .y("37.51207412593136")
                .build();

        List<KaKaoLocationDocument> kaoLocationDocuments = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            kaoLocationDocuments.add(kaKaoLocationDocument);
        }
        KakaoLocationResponse firstResponse = KakaoLocationResponse.builder()
                .kaKaoLocationMeta(kaKaoLocationMeta)
                .documents(kaoLocationDocuments)
                .build();

        kaKaoLocationMeta = KaKaoLocationMeta.builder()
                .kaKaoLocationSameName(kaKaoLocationSameName)
                .pageableCount(14)
                .totalCount(14)
                .isEnd(true)
                .build();

        KakaoLocationResponse secondResponse = KakaoLocationResponse.builder()
                .kaKaoLocationMeta(kaKaoLocationMeta)
                .documents(kaoLocationDocuments)
                .build();

        given(kakaoExternalApi.searchByKeword(any(), any(Integer.class), any(Integer.class))).willReturn(firstResponse).willReturn(secondResponse);

        // when
        List<LocationClientResponse> result = kakaoExternalRestFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);

        // then
        assertThat(result.size()).isEqualTo(totalSize);
        assertThat(result.get(0).getName()).isEqualTo(placeName);

    }

}