package com.location.api.server.search.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.location.api.server.search.dto.response.SearchResponse;
import com.location.api.server.search.controller.SearchController;
import com.location.api.server.search.service.query.SearchQueryService;
import com.location.api.server.testsupport.ApiDocTest;
import com.location.api.server.testsupport.ApiDocumentFormatGenerator;
import com.location.api.server.testsupport.ApiDocumentsUtil;
import com.location.api.server.testsupport.ApiMockMvcFactory;
import com.location.common.code.ApiResponseCode;
import com.location.common.converter.LocalDateTimeJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ApiDocTest
@DisplayName("검색 API")
public class SearchControllerDocs {

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @InjectMocks
    private SearchController controller;

    @Mock
    private SearchQueryService queryService;


    @BeforeEach
    void init(RestDocumentationContextProvider restDocumentation) {
        controller = new SearchController(queryService);
        mockMvc = ApiMockMvcFactory.getRestdocsMockMvc(restDocumentation, controller);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer());
        objectMapper.registerModule(simpleModule);
    }

    @Test
    @DisplayName("keyword로 장소 조회")
    void docs01() throws Exception {
        // given
        final List<SearchResponse> response = List.of(SearchResponse.builder()
                        .name("카카오")
                        .address("경기 성남시 분당구 백현동 532")
                        .roadAddress("경기 성남시 분당구 판교역로 166")
                .build());

        given(queryService.searchLocationByKeyword(any())).willReturn(response);

        // expect
        this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/locations/search")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(MockMvcRestDocumentation.document("search-by-keyword",
                ApiDocumentsUtil.getDocumentRequest(),
                ApiDocumentsUtil.getDocumentResponse(),
                requestParameters(
                        parameterWithName("keyword").attributes(ApiDocumentFormatGenerator.getRequiredFalse(), ApiDocumentFormatGenerator.getEmptyFormat()).description("검색어")
                ),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과"),
                        fieldWithPath("data[].name").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("장소명"),
                        fieldWithPath("data[].address").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("주소"),
                        fieldWithPath("data[].roadAddress").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("도로명 주소")
                )
        ));

    }

}
