package com.location.api.server.domain;

import com.location.api.server.dto.response.SearchResponse;
import com.location.common.holder.CooridinateErrorRangeHolder;
import com.location.external.client.spec.dto.LocationClientResponse;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class LocationInformation {

    private List<Location> locationList;

    @Builder
    public LocationInformation(List<Location> locationList) {
        this.locationList = locationList;
    }

    public static LocationInformation from(List<LocationClientResponse> responses) {
        return new LocationInformation(responses.stream()
                .map(LocationInformation::build)
                .toList()
        );
    }

    private static Location build(LocationClientResponse locationClientResponse) {
        return Location.builder()
                .name(locationClientResponse.getName())
                .coordinate(Coordinate.from(locationClientResponse.getCoordinateDto()))
                .address(Address.from(locationClientResponse.getAddressDto()))
                .build();
    }


    public int size() {
        return this.locationList.size();
    }

    public Location get(int index) {
        return this.locationList.get(index);
    }

    public boolean isEmpty() {
        return this.locationList == null || this.locationList.isEmpty();
    }

    public List<Location> getAll() {
        return this.locationList;
    }

    public List<String> getNames() {
        return this.locationList.stream()
                .map(Location::getName)
                .toList();
    }

    public void remove(Location location) {
        List<Location> updatedList = new ArrayList<>(this.locationList);
        updatedList.remove(location);
        this.locationList = updatedList;
    }

    public void remove(List<Location> locationList) {
        locationList.forEach(this::remove);
    }


    public List<SearchResponse> findCountLocationAndRemove(LocationInformation naverLocationInformation,
                                                           CooridinateErrorRangeHolder cooridinateErrorRangeHolder) {
        List<SearchResponse> result = new ArrayList<>();
        List<Location> kakaoToRemove = new ArrayList<>();
        List<Location> naverToRemove = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            if (i == 5) break;
            Location kakaoInformation = this.locationList.get(i);
            for (Location naverInformation : naverLocationInformation.getAll()) {
                if (kakaoInformation.isEquals(naverInformation, cooridinateErrorRangeHolder)) {
                    result.add(kakaoInformation.toResponse());
                    kakaoToRemove.add(kakaoInformation);
                    naverToRemove.add(naverInformation);
                }
            }
        }
        remove(kakaoToRemove);
        naverLocationInformation.remove(naverToRemove);
        return result;
    }

    public List<SearchResponse> findCountLocationAndRemove(int findCount) {
        List<SearchResponse> result = new ArrayList<>();
        List<Location> toRemove = new ArrayList<>();

        if (isEmpty()) return result;
        for (int i = 0; i < Math.min(findCount, locationList.size()); i++) {
            result.add(locationList.get(i).toResponse());
            toRemove.add(locationList.get(i));
        }
        remove(toRemove);

        return result;
    }

    public List<SearchResponse> findLocation() {
        return this.locationList.stream().map(Location::toResponse).toList();
    }
}
