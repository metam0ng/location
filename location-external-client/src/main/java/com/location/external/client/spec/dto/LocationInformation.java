package com.location.external.client.spec.dto;

import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.rest.dto.response.kakao.KaKaoLocationDocument;
import com.location.external.client.rest.dto.response.naver.NaverLocationItem;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LocationInformation {

    private String name;
    private Coordinate coordinate;

    @Builder
    public LocationInformation(String name,
                               Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    public static LocationInformation fromKakao(KaKaoLocationDocument document) {
        return LocationInformation.builder()
                .name(document.getPlaceName())
                .coordinate(Coordinate.of(Double.parseDouble(document.getX()), Double.parseDouble(document.getY())))
                .build();
    }

    public static LocationInformation fromNaver(NaverLocationItem item, CoordinateConverter coordinateConverter) {
        Coordinate coordinate = coordinateConverter.convertKATECHToLongitude(Double.parseDouble(item.getMapx()), Double.parseDouble(item.getMapy()));
        return LocationInformation.builder()
                .name(removeBTagAndStrip(item.getTitle()))
                .coordinate(coordinate)
                .build();
    }

    private static String removeBTagAndStrip(String title) {
        String temp = title.replaceAll("</b>|<b>", " ");
        return temp.replace("  ", " ").strip();
    }

    public Double getX() {
        return this.coordinate.getX();
    }

    public Double getY() {
        return this.coordinate.getY();
    }

    public boolean isEquals(LocationInformation locationInformation) {
        return this.coordinate.isEquals(locationInformation.coordinate);
    }
}
