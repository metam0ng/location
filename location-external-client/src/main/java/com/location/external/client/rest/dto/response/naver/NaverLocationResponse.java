package com.location.external.client.rest.dto.response.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class NaverLocationResponse {

    @JsonProperty("items")
    private List<NaverLocationItem> naverLocationItems;
    @JsonProperty("display")
    private int display;
    @JsonProperty("start")
    private int start;
    @JsonProperty("total")
    private int total;
    @JsonProperty("lastBuildDate")
    private String lastBuildDate;

    @Builder
    public NaverLocationResponse(List<NaverLocationItem> naverLocationItems,
                                 int display,
                                 int start,
                                 int total,
                                 String lastBuildDate) {
        this.naverLocationItems = naverLocationItems;
        this.display = display;
        this.start = start;
        this.total = total;
        this.lastBuildDate = lastBuildDate;
    }

}
