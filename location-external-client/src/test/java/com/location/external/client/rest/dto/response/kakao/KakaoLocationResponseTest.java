package com.location.external.client.rest.dto.response.kakao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoLocationResponseTest {

    @Test
    void meta의_페이지가_마지막이면_ture를_return한다() {
        // given
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
                .placeName("카카오프렌즈 코엑스점")
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

        // when
        boolean result = response.isEnd(14);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void kaKaoLocationDocuments_의_size를_return한다() {
        // given
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
                .placeName("카카오프렌즈 코엑스점")
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

        // when
        int result = response.getDocumentsSize();

        // then
        assertThat(result).isEqualTo(1);
    }

}