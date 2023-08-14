package com.location.external.client.rest.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class KakaoLocationResponse {

    @JsonProperty("documents")
    private List<KaKaoLocationDocument> documents;
    @JsonProperty("meta")
    private KaKaoLocationMeta kaKaoLocationMeta;

    @Builder
    public KakaoLocationResponse(List<KaKaoLocationDocument> documents,
                                 KaKaoLocationMeta kaKaoLocationMeta) {
        this.documents = documents;
        this.kaKaoLocationMeta = kaKaoLocationMeta;
    }

    public boolean isEnd(int totalSize) {
        return this.kaKaoLocationMeta.isEnd() || isDocumentsEqualsOrBiggerThenTotalSize(totalSize);
    }

    public int getDocumentsSize() {
        return this.documents.size();
    }

    public boolean isDocumentsEqualsOrBiggerThenTotalSize(int totalSize) {
        return this.getDocumentsSize() >= totalSize;
    }
}
