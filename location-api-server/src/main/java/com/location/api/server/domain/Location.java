package com.location.api.server.domain;

import com.location.common.holder.ErrorRangeHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Location {

    private String name;
    private Coordinate coordinate;

    @Builder
    public Location(String name,
                    Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    public Double getX() {
        return this.coordinate.getX();
    }

    public Double getY() {
        return this.coordinate.getY();
    }

    public boolean isEquals(Location location,
                            ErrorRangeHolder errorRangeHolder) {
        return this.coordinate.isEquals(location.coordinate, errorRangeHolder);
    }
}
