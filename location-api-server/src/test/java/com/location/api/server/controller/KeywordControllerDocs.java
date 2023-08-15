package com.location.api.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.service.query.KeywordQueryService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ApiDocTest
@DisplayName("키워드 API")
public class KeywordControllerDocs {

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @InjectMocks
    private KeywordController controller;

    @Mock
    private KeywordQueryService queryService;


    @BeforeEach
    void init(RestDocumentationContextProvider restDocumentation) {
        controller = new KeywordController(queryService);
        mockMvc = ApiMockMvcFactory.getRestdocsMockMvc(restDocumentation, controller);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer());
        objectMapper.registerModule(simpleModule);
    }

    @Test
    @DisplayName("사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드 목록을 제공한다")
    void docs01() throws Exception {
        // given
        List<KeywordResponse> response = List.of(
                KeywordResponse.builder().keyword("카카오").count(100000000L).build(),
                KeywordResponse.builder().keyword("카카오 뱅크").count(10000000L).build(),
                KeywordResponse.builder().keyword("네이버").count(1000000L).build(),
                KeywordResponse.builder().keyword("네이버 지도").count(10000L).build(),
                KeywordResponse.builder().keyword("카카오 페이").count(1000L).build(),
                KeywordResponse.builder().keyword("카카오 프렌즈").count(100L).build(),
                KeywordResponse.builder().keyword("뱅크").count(99L).build(),
                KeywordResponse.builder().keyword("은행").count(87L).build(),
                KeywordResponse.builder().keyword("지하철").count(1L).build()
        );
        given(queryService.findKeywordTop10()).willReturn(response);

        // expect
        this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/locations/keywords")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(MockMvcRestDocumentation.document("keyword-top-10",
                ApiDocumentsUtil.getDocumentRequest(),
                ApiDocumentsUtil.getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과"),
                        fieldWithPath("data[].keyword").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("검색어"),
                        fieldWithPath("data[].count").type(JsonFieldType.NUMBER).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("검색수")
                )
        ));

    }

}
