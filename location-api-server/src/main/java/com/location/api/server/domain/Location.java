package com.location.api.server.domain;

import com.location.api.server.dto.response.SearchResponse;
import com.location.common.holder.CooridinateErrorRangeHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Location {

    private String name;
    private Coordinate coordinate;
    private Address address;

    @Builder
    public Location(String name,
                    Coordinate coordinate,
                    Address address) {
        this.name = name;
        this.coordinate = coordinate;
        this.address = address;
    }

    public Double getX() {
        return this.coordinate.getX();
    }

    public Double getY() {
        return this.coordinate.getY();
    }

    public boolean isEquals(Location location,
                            CooridinateErrorRangeHolder cooridinateErrorRangeHolder) {
        return this.coordinate.isEquals(location.coordinate, cooridinateErrorRangeHolder);
    }

    public SearchResponse toResponse() {
        return SearchResponse.builder()
                .name(this.name)
                .address(this.address.getAddress())
                .roadAddress(this.address.getRoadAddress())
                .build();
    }
}
