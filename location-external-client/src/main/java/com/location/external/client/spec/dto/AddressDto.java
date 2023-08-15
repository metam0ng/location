package com.location.external.client.spec.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressDto {

    private String address;
    private String roadAddress;

    @Builder
    public AddressDto(String address,
                      String roadAddress) {
        this.roadAddress = roadAddress;
        this.address = address;
    }

    public static AddressDto of(String address,
                                String roadAddress) {
        return AddressDto.builder()
                .address(address)
                .roadAddress(roadAddress)
                .build();
    }
}
