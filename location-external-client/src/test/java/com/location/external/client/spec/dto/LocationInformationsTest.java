package com.location.external.client.spec.dto;

import com.location.external.client.rest.dto.response.kakao.KaKaoLocationDocument;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationMeta;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationSameName;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import com.location.external.client.rest.dto.response.naver.NaverLocationItem;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import com.location.external.client.rest.converter.CoordinateConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationInformationsTest {

    @Test
    void KakaoLocationResponse를_LocationInformations로_변경_할_수_있다() {
        // given
        String placeName = "카카오프렌즈 코엑스점";
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

        // when
        LocationInformations result = LocationInformations.fromKakao(List.of(response));

        // then
        assertThat(result.size()).isEqualTo(response.getDocuments().size());
        assertThat(result.get(0).getName()).isEqualTo(placeName);
    }

    @Test
    void NaverLocationResponse를_LocationInformations로_변경_할_수_있다() {
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
        NaverLocationResponse response = NaverLocationResponse.builder()
                .naverLocationItems(List.of(naverLocationItem))
                .display(1)
                .start(1)
                .total(1)
                .lastBuildDate("Sat, 12 Aug 2023 15:05:06 +0900")
                .build();

        // when
        LocationInformations result = LocationInformations.fromNaver(response, new CoordinateConverter());

        // then
        assertThat(result.size()).isEqualTo(response.getNaverLocationItems().size());
        assertThat(result.get(0).getName()).isEqualTo(title);
    }

    @Test
    void LocationInformations의_size를_구할_수_있다() {
        // given
        List<LocationInformation> locationInformation = List.of(LocationInformation.builder()
                .name("카카오프렌즈 코엑스점")
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .build());
        LocationInformations response = LocationInformations.from(locationInformation);

        // when
        int result = response.size();

        // then
        assertThat(result).isEqualTo(locationInformation.size());
    }

    @Test
    void LocationInformations의_get을_구할_수_있다() {
        // given
        String name = "카카오프렌즈 코엑스점";
        Double x = 126.981682046205;
        Double y = 37.5635252637636;
        List<LocationInformation> locationInformation = List.of(LocationInformation.builder()
                .name(name)
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .build());
        LocationInformations response = LocationInformations.from(locationInformation);

        // when
        LocationInformation result = response.get(0);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getX()).isEqualTo(x);
        assertThat(result.getY()).isEqualTo(y);
    }

    @Test
    void LocationInformations의_getName을_구할_수_있다() {
        // given
        String name = "카카오프렌즈 코엑스점";
        Double x = 126.981682046205;
        Double y = 37.5635252637636;
        List<LocationInformation> locationInformation = List.of(LocationInformation.builder()
                .name(name)
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .build());
        LocationInformations response = LocationInformations.from(locationInformation);

        // when
        List<String> result = response.getNames();

        // then
        assertThat(result.size()).isEqualTo(locationInformation.size());
        assertThat(result.get(0)).isEqualTo(name);
    }

    @Test
    void LocationInformation로_요소를_삭제_할_수_있다() {
        // given
        LocationInformation locationInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformations locationInformations = LocationInformations.from(List.of(locationInformation));

        // when
        locationInformations.remove(locationInformation);

        // then
        assertThat(locationInformations.isEmpty()).isTrue();
    }

    @Test
    void List_LocationInformation로_요소를_삭제_할_수_있다() {
        // given
        LocationInformation locationInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformations locationInformations = LocationInformations.from(List.of(locationInformation));

        // when
        locationInformations.remove(List.of(locationInformation));

        // then
        assertThat(locationInformations.isEmpty()).isTrue();

    }

    @Test
    void 서로_다른_LocationInformation_의_중복된_장소를_찾고_대상을_제거한다() {
        // given
        LocationInformation firstInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformations firstInformations = LocationInformations.from(List.of(firstInformation));

        LocationInformation secondInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformations secondInformations = LocationInformations.from(List.of(secondInformation));

        // when
        List<String> result = firstInformations.findSameLocationAndRemove(secondInformations);

        //then
        assertThat(result.get(0)).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(firstInformations.isEmpty()).isTrue();
    }

    @Test
    void 모든_LocationInformation의_장소를_찾을_수_있다() {
        // given
        LocationInformation firstInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformation secondInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 강남점")
                .coordinate(Coordinate.of(130.57066130083415, 36.450682729588145))
                .build();
        LocationInformations locationInformations = LocationInformations.from(List.of(firstInformation, secondInformation));

        // when
        List<String> result = locationInformations.findLocationName();

        // then
        assertThat(result.get(0)).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(result.get(1)).isEqualTo("카카오 프렌즈 강남점");
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void count수_만큼_LocationInformation의_장소를_찾을_수_있다() {
        // given
        LocationInformation firstInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .build();
        LocationInformation secondInformation =  LocationInformation.builder()
                .name("카카오 프렌즈 강남점")
                .coordinate(Coordinate.of(130.57066130083415, 36.450682729588145))
                .build();
        LocationInformations locationInformations = LocationInformations.from(List.of(firstInformation, secondInformation));

        // when
        List<String> result = locationInformations.findLocationName(1);

        // then
        assertThat(result.get(0)).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(result.size()).isEqualTo(1);
    }
}