package com.location.external.client.rest;

import com.location.external.client.rest.api.NaverExternalApi;
import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.rest.dto.response.naver.NaverLocationItem;
import com.location.external.client.rest.dto.response.naver.NaverLocationResponse;
import com.location.external.client.spec.code.ApiType;
import com.location.external.client.spec.dto.LocationClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NaverExternalRestFetcherTest {

    @InjectMocks
    private NaverExternalRestFetcher naverExternalRestFetcher;
    @Mock
    private NaverExternalApi naverExternalApi;

    @BeforeEach
    void setUp() {
        naverExternalRestFetcher = new NaverExternalRestFetcher(naverExternalApi, new CoordinateConverter());
    }

    @Test
    void ExternalType의_NAVER를_지원한다() {
        // given
        ApiType type = ApiType.NAVER;

        // when
        boolean result = naverExternalRestFetcher.isSupport(type);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void ExternalType의_KAKAO를_지원하지_않는다() {
        // given
        ApiType type = ApiType.KAKAO;

        // when
        boolean result = naverExternalRestFetcher.isSupport(type);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 키워드로_네이버_장소를_검색_할_수_있다() {
        // given
        String keyword = "카카오프렌즈";
        String title = "카카오프렌즈 롯데영플라자 명동점";
        int pageSize = 5;
        int totalSize = 10;
        NaverLocationItem naverLocationItem = NaverLocationItem.builder()
                .title(title)
                .link("http://store.kakaofriends.com")
                .category("도서,음반,문구>문구,팬시용품")
                .telephone("")
                .address("서울특별시 중구 남대문로2가 123 롯데영플라자 1층")
                .roadAddress("서울특별시 중구 남대문로 67 롯데영플라자 1층")
                .mapx("310240")
                .mapy("551739")
                .build();
        NaverLocationResponse response = NaverLocationResponse.builder()
                .naverLocationItems(List.of(naverLocationItem))
                .display(1)
                .start(1)
                .total(1)
                .lastBuildDate("Sat, 12 Aug 2023 15:05:06 +0900")
                .build();

        given(naverExternalApi.searchByKeword(any(), any(Integer.class), any(Integer.class))).willReturn(response);

        // when
        List<LocationClientResponse> result = naverExternalRestFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);

        // then
        assertThat(result.size()).isEqualTo(response.getNaverLocationItems().size());
        assertThat(result.get(0).getName()).isEqualTo(title);

    }

    @Test
    void size가_5개일때_5개의_이름이_반환된다() {
        // given
        String keyword = "카카오프렌즈";
        String title = "카카오프렌즈 롯데영플라자 명동점";
        int pageSize = 5;
        int totalSize = 5;
        NaverLocationItem naverLocationItem = NaverLocationItem.builder()
                .title(title)
                .link("http://store.kakaofriends.com")
                .category("도서,음반,문구>문구,팬시용품")
                .telephone("")
                .address("서울특별시 중구 남대문로2가 123 롯데영플라자 1층")
                .roadAddress("서울특별시 중구 남대문로 67 롯데영플라자 1층")
                .mapx("310240")
                .mapy("551739")
                .build();

        List<NaverLocationItem> naverLocationItems = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            naverLocationItems.add(naverLocationItem);
        }

        NaverLocationResponse firstResponse = NaverLocationResponse.builder()
                .naverLocationItems(naverLocationItems)
                .display(5)
                .start(1)
                .total(10)
                .lastBuildDate("Sat, 12 Aug 2023 15:05:06 +0900")
                .build();

        NaverLocationResponse secondResponse = NaverLocationResponse.builder()
                .naverLocationItems(naverLocationItems)
                .display(5)
                .start(2)
                .total(10)
                .lastBuildDate("Sat, 12 Aug 2023 15:05:06 +0900")
                .build();

        given(naverExternalApi.searchByKeword(any(), any(Integer.class), any(Integer.class))).willReturn(firstResponse).willReturn(secondResponse);

        // when
        List<LocationClientResponse> result = naverExternalRestFetcher.searchLocationByKeyword(keyword, pageSize, totalSize);

        // then
        assertThat(result.size()).isEqualTo(totalSize);
        assertThat(result.get(0).getName()).isEqualTo(title);
    }

}