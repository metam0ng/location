package com.location.api.server.search.domain;

import com.location.external.client.spec.dto.AddressDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {

    private String address;
    private String roadAddress;

    @Builder
    public Address(String address,
                   String roadAddress) {
        this.roadAddress = roadAddress;
        this.address = address;
    }

    public static Address from(AddressDto addressDto) {
        return Address.builder()
                .address(addressDto.getAddress())
                .roadAddress(addressDto.getRoadAddress())
                .build();
    }
}
