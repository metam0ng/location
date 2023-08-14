package com.location.external.client.rest.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class KaKaoLocationSameName {
    @JsonProperty("selected_region")
    private String selectedRegion;
    @JsonProperty("keyword")
    private String keyword;
    @JsonProperty("region")
    private List<String> region;

    @Builder
    public KaKaoLocationSameName(String selectedRegion,
                                 String keyword,
                                 List<String> region) {
        this.selectedRegion = selectedRegion;
        this.keyword = keyword;
        this.region = region;
    }
}
