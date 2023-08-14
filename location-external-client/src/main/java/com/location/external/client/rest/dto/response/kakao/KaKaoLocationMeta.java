package com.location.external.client.rest.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class KaKaoLocationMeta {
    @JsonProperty("is_end")
    private boolean isEnd;
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("pageable_count")
    private int pageableCount;
    @JsonProperty("same_name")
    private KaKaoLocationSameName kaKaoLocationSameName;

    @Builder
    public KaKaoLocationMeta(boolean isEnd,
                             int totalCount,
                             int pageableCount,
                             KaKaoLocationSameName kaKaoLocationSameName) {
        this.isEnd = isEnd;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.kaKaoLocationSameName = kaKaoLocationSameName;
    }
}
