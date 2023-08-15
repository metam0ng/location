package com.location.external.client.spec.dto;

import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationDocument;
import com.location.external.client.rest.dto.response.naver.NaverLocationItem;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LocationClientResponse {

    private String name;
    private CoordinateDto coordinateDto;

    @Builder
    public LocationClientResponse(String name,
                                  CoordinateDto coordinateDto) {
        this.name = name;
        this.coordinateDto = coordinateDto;
    }

    public static LocationClientResponse fromKakao(KaKaoLocationDocument document) {
        return LocationClientResponse.builder()
                .name(document.getPlaceName())
                .coordinateDto(CoordinateDto.of(Double.parseDouble(document.getX()), Double.parseDouble(document.getY())))
                .build();
    }

    public static LocationClientResponse fromNaver(NaverLocationItem item, CoordinateConverter coordinateConverter) {
        CoordinateDto CoordinateDto = coordinateConverter.convertKATECHToLongitude(Double.parseDouble(item.getMapx()), Double.parseDouble(item.getMapy()));
        return LocationClientResponse.builder()
                .name(removeBTagAndStrip(item.getTitle()))
                .coordinateDto(CoordinateDto)
                .build();
    }

    private static String removeBTagAndStrip(String title) {
        String temp = title.replaceAll("</b>|<b>", " ");
        return temp.replace("  ", " ").strip();
    }

    public Double getX() {
        return this.coordinateDto.getX();
    }

    public Double getY() {
        return this.coordinateDto.getY();
    }

}
