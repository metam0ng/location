package com.location.external.client.spec.dto;

import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.rest.dto.response.kakao.KakaoLocationResponse;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class LocationInformations {

    private List<LocationInformation> locationInformationList;

    @Builder
    public LocationInformations(List<LocationInformation> locationInformationList) {
        this.locationInformationList = locationInformationList;
    }

    public static LocationInformations from(List<LocationInformation> locationInformationList) {
        return new LocationInformations(locationInformationList);
    }

    public static LocationInformations fromKakao(List<KakaoLocationResponse> responses) {
        return new LocationInformations(responses.stream()
                .map(LocationInformations::getList)
                .flatMap(List::stream)
                .toList());
    }

    private static List<LocationInformation> getList(KakaoLocationResponse response) {
        return response.getDocuments().stream()
                .map(LocationInformation::fromKakao)
                .toList();
    }

    public static LocationInformations fromNaver(NaverLocationResponse response,
                                                 CoordinateConverter coordinateConverter) {
        return new LocationInformations(getList(response, coordinateConverter));
    }

    private static List<LocationInformation> getList(NaverLocationResponse response,
                                                     CoordinateConverter coordinateConverter) {
        return response.getNaverLocationItems().stream()
                .map(dto -> LocationInformation.fromNaver(dto, coordinateConverter))
                .toList();
    }

    public int size() {
        return this.locationInformationList.size();
    }

    public LocationInformation get(int index) {
        return this.locationInformationList.get(index);
    }


    public List<String> getNames() {
        return this.locationInformationList.stream()
                .map(LocationInformation::getName)
                .toList();
    }

    public List<LocationInformation> getAll() {
        return this.locationInformationList;
    }

    public void remove(LocationInformation locationInformation) {
        List<LocationInformation> updatedList = new ArrayList<>(this.locationInformationList);
        updatedList.remove(locationInformation);
        this.locationInformationList = updatedList;
    }

    public void remove(List<LocationInformation> locationInformationList) {
        locationInformationList.forEach(this::remove);
    }


    public List<String> findSameLocationAndRemove(LocationInformations naverLocationInformations) {
        List<String> result = new ArrayList<>();
        List<LocationInformation> kakaoToRemove = new ArrayList<>();
        List<LocationInformation> naverToRemove = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            if (i == 5) break;
            LocationInformation kakaoInformation = this.locationInformationList.get(i);
            for (LocationInformation naverInformation : naverLocationInformations.getAll()) {
                if (kakaoInformation.isEquals(naverInformation)) {
                    result.add(kakaoInformation.getName());
                    kakaoToRemove.add(kakaoInformation);
                    naverToRemove.add(naverInformation);
                }
            }
        }
        remove(kakaoToRemove);
        naverLocationInformations.remove(naverToRemove);
        return result;
    }

    public List<String> findLocationName(int findCount) {
        List<String> result = new ArrayList<>();
        if (isEmpty()) return result;
        int limit = Math.min(findCount, locationInformationList.size());
        for (int i = 0; i < limit; i++) {
            result.add(locationInformationList.get(i).getName());
        }
        return result;
    }

    public boolean isEmpty() {
        return this.locationInformationList == null || this.locationInformationList.isEmpty();
    }

    public List<String> findLocationName() {
        return this.locationInformationList.stream().map(LocationInformation::getName).toList();
    }
}
