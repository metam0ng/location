package com.location.external.client.spec.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CoordinateDto {

    private double x;
    private double y;

    @Builder
    public CoordinateDto(double x,
                         double y) {
        this.x = x;
        this.y = y;
    }

    public static CoordinateDto of(double x,
                                   double y) {
        return CoordinateDto.builder()
                .x(x)
                .y(y)
                .build();
    }

}
