package com.location.api.server.infrastructure.code;

import com.location.external.client.spec.code.ApiType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalTypeTest {

    @Test
    void ExternalType_KAKAO_으로_ApiType_KAKAO를_가지고_올_수_있다() {
        // given
        ExternalType externalType = ExternalType.KAKAO;

        // when
        ApiType result = externalType.getApiType();

        // then
        assertThat(result).isEqualTo(ApiType.KAKAO);
    }

    @Test
    void ExternalType_NAVER_으로_ApiType_NAVER를_가지고_올_수_있다() {
        // given
        ExternalType externalType = ExternalType.NAVER;

        // when
        ApiType result = externalType.getApiType();

        // then
        assertThat(result).isEqualTo(ApiType.NAVER);
    }
}