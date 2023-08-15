package com.location.api.server.search.domain;

import com.location.common.holder.CooridinateErrorRangeHolder;
import com.location.external.client.spec.dto.CoordinateDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Coordinate {

    private double x;
    private double y;

    @Builder
    public Coordinate(double x,
                      double y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate of(double x,
                                double y) {
        return Coordinate.builder()
                .x(x)
                .y(y)
                .build();
    }

    public static Coordinate from(CoordinateDto coordinateDto) {
        return Coordinate.builder()
                .x(coordinateDto.getX())
                .y(coordinateDto.getY())
                .build();
    }

    public boolean isEquals(Coordinate target,
                            CooridinateErrorRangeHolder cooridinateErrorRangeHolder) {
        // 좌표 비교
        return !(Math.abs(this.x - target.getX()) > cooridinateErrorRangeHolder.errorRange()) &&
                !(Math.abs(this.y - target.getY()) > cooridinateErrorRangeHolder.errorRange());
    }
}
