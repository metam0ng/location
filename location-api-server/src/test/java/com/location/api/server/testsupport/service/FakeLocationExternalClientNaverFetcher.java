package com.location.api.server.testsupport.service;

import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.CoordinateDto;
import com.location.external.client.spec.dto.LocationClientResponse;

import java.util.List;

public class FakeLocationExternalClientNaverFetcher implements LocationExternalClientFetcher {
    @Override
    public boolean isSupport(ApiType apiType) {
        return ApiType.NAVER == apiType;
    }

    @Override
    public List<LocationClientResponse> searchLocationByKeyword(String keyword,
                                                                int pageSize,
                                                                int totalSize) {
        if(keyword.equals("카카오")) {
            return List.of(
                    LocationClientResponse.builder()
                            .name("카카오 멜론")
                            .coordinateDto(CoordinateDto.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 톡대화내용복구")
                            .coordinateDto(CoordinateDto.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오  판교아지트")
                            .coordinateDto(CoordinateDto.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 고객센터")
                            .coordinateDto(CoordinateDto.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("카카오 톡 대화내용 데이터복구센터")
                            .coordinateDto(CoordinateDto.of(128.57066130083415, 33.450682729588145))
                            .build());
        }else if(keyword.equals("네이버")) {
            return List.of(
                    LocationClientResponse.builder()
                            .name("네이버 바이브")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 포인트")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 판교아지트")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 카페")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationClientResponse.builder()
                            .name("네이버 지식인")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build());
        } else if (keyword.equals("카카오 프렌즈")) {
            return List.of(
                    LocationClientResponse.builder()
                            .name("카카오 프렌즈 삼성점")
                            .coordinateDto(CoordinateDto.of(129.57066130083415, 33.450682729588145))
                            .build());
        } else {
            return null;
        }

    }
}
