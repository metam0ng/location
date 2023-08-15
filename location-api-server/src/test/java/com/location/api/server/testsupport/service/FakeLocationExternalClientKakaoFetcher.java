package com.location.api.server.testsupport.service;

import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.AddressDto;
import com.location.external.client.spec.dto.CoordinateDto;
import com.location.external.client.spec.dto.LocationClientResponse;

import java.util.List;

public class FakeLocationExternalClientKakaoFetcher implements LocationExternalClientFetcher {
    @Override
    public boolean isSupport(ApiType apiType) {
        return ApiType.KAKAO == apiType;
    }

    @Override
    public List<LocationClientResponse> searchLocationByKeyword(String keyword,
                                                                int pageSize,
                                                                int totalSize) {
        if(keyword.equals("카카오")){
            return List.of(
                    LocationClientResponse.builder()
                            .name("카카오")
                            .coordinateDto(CoordinateDto.of(127.1104335101161, 37.39570088983171))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 스페이스닷원")
                            .coordinateDto(CoordinateDto.of(126.57066130083415, 33.450682729588145))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 스페이스닷투")
                            .coordinateDto(CoordinateDto.of(126.570875463183, 33.4526219140826))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오프렌즈 판교아지트점")
                            .coordinateDto(CoordinateDto.of(127.1100869772751, 37.39581744474611))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오판교아지트")
                            .coordinateDto(CoordinateDto.of(127.11036420512991, 37.39541713271851))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오뱅크")
                            .coordinateDto(CoordinateDto.of(127.112119228848, 37.395455126629855))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오모빌리티")
                            .coordinateDto(CoordinateDto.of(127.1101250888609, 37.39407843730005))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("판교 H스퀘어 (카카오 판교사옥) 전기차충전소 (테슬라전용)")
                            .coordinateDto(CoordinateDto.of(127.10863811607922, 37.4021232296443))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오엔터테인먼트 판교오피스(스토리부문)")
                            .coordinateDto(CoordinateDto.of(127.108996408808, 37.4007470412071))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오브이엑스")
                            .coordinateDto(CoordinateDto.of(127.10878160491359, 37.401377953592146))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build());
        } else if (keyword.equals("네이버")) {
            return List.of(
                    LocationClientResponse.builder()
                            .name("네이버 바이브")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 포인트")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 판교아지트")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 쇼핑")
                            .coordinateDto(CoordinateDto.of(127.1100869772751, 37.39581744474611))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 뉴스")
                            .coordinateDto(CoordinateDto.of(127.11036420512991, 37.39541713271851))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 예약")
                            .coordinateDto(CoordinateDto.of(127.112119228848, 37.395455126629855))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 웹툰")
                            .coordinateDto(CoordinateDto.of(127.1101250888609, 37.39407843730005))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 그린팩토리")
                            .coordinateDto(CoordinateDto.of(127.10863811607922, 37.4021232296443))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 메일")
                            .coordinateDto(CoordinateDto.of(127.108996408808, 37.4007470412071))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 블로그")
                            .coordinateDto(CoordinateDto.of(127.10878160491359, 37.401377953592146))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build());

        } else if (keyword.equals("카카오 프렌즈")) {
            return List.of(
                    LocationClientResponse.builder()
                            .name("카카오 프렌즈")
                            .coordinateDto(CoordinateDto.of(127.1104335101161, 37.39570088983171))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 프렌즈 스페이스닷원")
                            .coordinateDto(CoordinateDto.of(126.57066130083415, 33.450682729588145))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 프렌즈 스페이스닷투")
                            .coordinateDto(CoordinateDto.of(126.570875463183, 33.4526219140826))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오프렌즈 프렌즈 판교아지트점")
                            .coordinateDto(CoordinateDto.of(127.1100869772751, 37.39581744474611))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오판교아지트")
                            .coordinateDto(CoordinateDto.of(127.11036420512991, 37.39541713271851))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오뱅크")
                            .coordinateDto(CoordinateDto.of(127.112119228848, 37.395455126629855))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오모빌리티")
                            .coordinateDto(CoordinateDto.of(127.1101250888609, 37.39407843730005))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("판교 H스퀘어 (카카오 판교사옥) 전기차충전소 (테슬라전용)")
                            .coordinateDto(CoordinateDto.of(127.10863811607922, 37.4021232296443))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오엔터테인먼트 판교오피스(스토리부문)")
                            .coordinateDto(CoordinateDto.of(127.108996408808, 37.4007470412071))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오브이엑스")
                            .coordinateDto(CoordinateDto.of(127.10878160491359, 37.401377953592146))
                            .addressDto(AddressDto.builder().address("경기 성남시 분당구 백현동 ").roadAddress("경기 성남시 분당구 판교역로 166").build())
                            .build());
        } else {
            return null;
        }

    }
}
