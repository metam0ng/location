package com.location.api.server.testsupport.service;

import com.location.external.client.spec.LocationExternalClientFetcher;
import com.location.external.client.spec.code.ExternalType;
import com.location.external.client.spec.dto.Coordinate;
import com.location.external.client.spec.dto.LocationInformation;
import com.location.external.client.spec.dto.LocationInformations;

import java.util.List;

public class LocationExternalClientNaverTestFetcher implements LocationExternalClientFetcher {
    @Override
    public boolean isSupport(ExternalType externalType) {
        return ExternalType.NAVER == externalType;
    }

    @Override
    public LocationInformations searchLocationByKeyword(String keyword,
                                                        int pageSize,
                                                        int totalSize) {
        if(keyword.equals("카카오")) {
            return LocationInformations.from(List.of(
                    LocationInformation.builder()
                            .name("카카오 멜론")
                            .coordinate(Coordinate.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("카카오 톡대화내용복구")
                            .coordinate(Coordinate.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("카카오  판교아지트")
                            .coordinate(Coordinate.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("카카오 고객센터")
                            .coordinate(Coordinate.of(128.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("카카오 톡 대화내용 데이터복구센터")
                            .coordinate(Coordinate.of(128.57066130083415, 33.450682729588145))
                            .build())
            );
        }else if(keyword.equals("네이버")) {
            return LocationInformations.from(List.of(
                    LocationInformation.builder()
                            .name("네이버 바이브")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("네이버 포인트")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("네이버 판교아지트")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("네이버 카페")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build(),
                    LocationInformation.builder()
                            .name("네이버 지식인")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build())
            );
        } else if (keyword.equals("카카오 프렌즈")) {
            return LocationInformations.from(List.of(
                    LocationInformation.builder()
                            .name("카카오 프렌즈 삼성점")
                            .coordinate(Coordinate.of(129.57066130083415, 33.450682729588145))
                            .build()));
        } else {
            return null;
        }

    }
}
