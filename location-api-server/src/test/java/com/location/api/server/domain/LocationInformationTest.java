package com.location.api.server.domain;

import com.location.api.server.dto.response.SearchResponse;
import com.location.api.server.testsupport.service.FakeCooridinateErrorRangeHolder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationInformationTest {

    @Test
    void LocationInformation의_size를_구할_수_있다() {
        // given
        List<Location> location = List.of(Location.builder()
                .name("카카오프렌즈 코엑스점")
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .build());
        LocationInformation response = LocationInformation.builder()
                .locationList(location)
                .build();

        // when
        int result = response.size();

        // then
        assertThat(result).isEqualTo(location.size());
    }

    @Test
    void LocationInformation의_get을_구할_수_있다() {
        // given
        String name = "카카오프렌즈 코엑스점";
        Double x = 126.981682046205;
        Double y = 37.5635252637636;
        List<Location> location = List.of(Location.builder()
                .name(name)
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build());
        LocationInformation response = LocationInformation.builder()
                .locationList(location)
                .build();

        // when
        Location result = response.get(0);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getX()).isEqualTo(x);
        assertThat(result.getY()).isEqualTo(y);
    }

    @Test
    void LocationInformation의_getName을_구할_수_있다() {
        // given
        String name = "카카오프렌즈 코엑스점";
        Double x = 126.981682046205;
        Double y = 37.5635252637636;
        List<Location> location = List.of(Location.builder()
                .name(name)
                .coordinate(Coordinate.of(126.981682046205, 37.5635252637636))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build());
        LocationInformation response = LocationInformation.builder()
                .locationList(location)
                .build();

        // when
        List<String> result = response.getNames();

        // then
        assertThat(result.size()).isEqualTo(location.size());
        assertThat(result.get(0)).isEqualTo(name);
    }

    @Test
    void LocationInformation로_요소를_삭제_할_수_있다() {
        // given
        Location location = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        LocationInformation locationInformation = LocationInformation.builder()
                .locationList(List.of(location))
                .build();

        // when
        locationInformation.remove(location);

        // then
        assertThat(locationInformation.isEmpty()).isTrue();
    }

    @Test
    void List_LocationInformation로_요소를_삭제_할_수_있다() {
        // given
        Location location = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        LocationInformation locationInformation = LocationInformation.builder()
                .locationList(List.of(location))
                .build();

        // when
        locationInformation.remove(List.of(location));

        // then
        assertThat(locationInformation.isEmpty()).isTrue();

    }

    @Test
    void 서로_다른_LocationInformation_의_중복된_장소를_찾고_대상을_제거한다() {
        // given
        Location firstInformation = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        LocationInformation firstInformations = LocationInformation.builder()
                .locationList(List.of(firstInformation))
                .build();

        Location secondInformation = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        LocationInformation secondInformations = LocationInformation.builder()
                .locationList(List.of(secondInformation))
                .build();

        // when
        List<SearchResponse> result = firstInformations.findCountLocationAndRemove(secondInformations, new FakeCooridinateErrorRangeHolder());

        //then
        assertThat(result.get(0).getName()).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(firstInformations.isEmpty()).isTrue();
    }


    @Test
    void LocationInformation_장소를_count_수_만큼_찾고_대상을_제거한다() {
        // given
        Location firstInformation = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();

        Location secondInformation = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();

        LocationInformation locationInformation = LocationInformation.builder()
                .locationList(List.of(firstInformation, secondInformation))
                .build();

        // when
        List<SearchResponse> result = locationInformation.findCountLocationAndRemove(1);

        //then
        assertThat(result.get(0).getName()).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(locationInformation.size()).isEqualTo(1);
    }
    @Test
    void 모든_LocationInformation의_장소를_찾을_수_있다() {
        // given
        Location firstInformation = Location.builder()
                .name("카카오 프렌즈 삼성점")
                .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        Location secondInformation = Location.builder()
                .name("카카오 프렌즈 강남점")
                .coordinate(Coordinate.of(130.57066130083415, 36.450682729588145))
                .address(Address.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                .build();
        LocationInformation locationInformation = LocationInformation.builder()
                .locationList(List.of(firstInformation, secondInformation))
                .build();

        // when
        List<SearchResponse> result = locationInformation.findLocation();

        // then
        assertThat(result.get(0).getName()).isEqualTo("카카오 프렌즈 삼성점");
        assertThat(result.get(1).getName()).isEqualTo("카카오 프렌즈 강남점");
        assertThat(result.size()).isEqualTo(2);
    }

}