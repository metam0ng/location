package com.location.external.client.spec.dto;

import com.location.external.client.rest.dto.response.kakao.KaKaoLocationDocument;
import com.location.external.client.rest.dto.response.naver.NaverLocationItem;
import com.location.external.client.rest.converter.CoordinateConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationClientResponseTest {

    @Test
    void KaKaoLocationDocument가_LocationClientResponse로_변환_된다() {
        // given
        String name = "카카오프렌즈 코엑스점";
        KaKaoLocationDocument kaKaoLocationDocument = KaKaoLocationDocument.builder()
                .placeName(name)
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

        // when
        LocationClientResponse result = LocationClientResponse.fromKakao(kaKaoLocationDocument);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getX()).isEqualTo(127.05902969025047);
        assertThat(result.getY()).isEqualTo(37.51207412593136);
    }

    @Test
    void NaverLocationItem이_LocationClientResponse로_변환_된다() {
        // given
        String title = "카카오프렌즈 롯데영플라자 명동점";
        NaverLocationItem naverLocationItem = NaverLocationItem.builder()
                .title(title)
                .link("http://store.kakaofriends.com")
                .category("도서,음반,문구>문구,팬시용품")
                .telephone("")
                .address("서울특별시 중구 남대문로2가 123 롯데영플라자 1층")
                .roadAddress("서울특별시 중구 남대문로 67 롯데영플라자 1층")
                .mapx("310240")
                .mapy("551739")
                .build();

        // when
        LocationClientResponse result = LocationClientResponse.fromNaver(naverLocationItem, new CoordinateConverter());

        // then
        assertThat(result.getName()).isEqualTo(title);
        assertThat(result.getX()).isEqualTo(126.98177443521772);
        assertThat(result.getY()).isEqualTo(37.56351989777749);
    }

}