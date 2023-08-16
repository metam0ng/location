package com.location.api.server.circuit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.location.api.server.circuit.dto.response.CircuitBreakerResponse;
import com.location.api.server.circuit.service.CircuitBreakerService;
import com.location.api.server.testsupport.ApiDocTest;
import com.location.api.server.testsupport.ApiDocumentFormatGenerator;
import com.location.api.server.testsupport.ApiDocumentsUtil;
import com.location.api.server.testsupport.ApiMockMvcFactory;
import com.location.common.code.ApiResponseCode;
import com.location.common.converter.LocalDateTimeJsonConverter;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
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
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ApiDocTest
@DisplayName("서킷 브레이커 API")
class CircuitBreakerControllerDocs {

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @InjectMocks
    private CircuitBreakerController controller;

    @Mock
    private CircuitBreakerService service;

    @BeforeEach
    void init(RestDocumentationContextProvider restDocumentation) {
        controller = new CircuitBreakerController(service);
        mockMvc = ApiMockMvcFactory.getRestdocsMockMvc(restDocumentation, controller);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer());
        objectMapper.registerModule(simpleModule);
    }

    @Test
    @DisplayName("서킷 브레이커를 close다.")
    void docs01() throws Exception {
        // given
        doNothing().when(service).close(any());

        // expect
        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/locations/circuit/close")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .param("name", "dapi.kakao.com")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("circuit-breaker-close",
                        ApiDocumentsUtil.getDocumentRequest(),
                        ApiDocumentsUtil.getDocumentResponse(),
                        requestParameters(
                                parameterWithName("name").attributes(ApiDocumentFormatGenerator.getRequiredFalse(),
                                        ApiDocumentFormatGenerator.getEmptyFormat()).description("close 대상")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                                fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                                fieldWithPath("data").type(JsonFieldType.NULL).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과")
                        )
                ));

    }

    @Test
    @DisplayName("서킷 브레이커를 open한다.")
    void docs02() throws Exception {
        // given
        doNothing().when(service).open(any());

        // expect
        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/locations/circuit/open")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .param("name", "dapi.kakao.com")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("circuit-breaker-open",
                        ApiDocumentsUtil.getDocumentRequest(),
                        ApiDocumentsUtil.getDocumentResponse(),
                        requestParameters(
                                parameterWithName("name").attributes(ApiDocumentFormatGenerator.getRequiredFalse(),
                                        ApiDocumentFormatGenerator.getEmptyFormat()).description("open 대상")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                                fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                                fieldWithPath("data").type(JsonFieldType.NULL).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과")
                        )
                ));

    }

    @Test
    @DisplayName("서킷 브레이커의 status를 조회한다.")
    void docs03() throws Exception {
        // given
        given(service.status(any())).willReturn(CircuitBreaker.State.CLOSED);

        // expect
        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/locations/circuit/status")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .param("name", "dapi.kakao.com")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("circuit-breaker-status",
                        ApiDocumentsUtil.getDocumentRequest(),
                        ApiDocumentsUtil.getDocumentResponse(),
                        requestParameters(
                                parameterWithName("name").attributes(ApiDocumentFormatGenerator.getRequiredFalse(),
                                        ApiDocumentFormatGenerator.getEmptyFormat()).description("조회 대상")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                                fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                                fieldWithPath("data").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과")
                        )
                ));
    }

    @Test
    @DisplayName("모든 서킷 브레이커를 조회한다.")
    void docs04() throws Exception {
        // given
        List<CircuitBreakerResponse> response = List.of(CircuitBreakerResponse.builder()
                        .name("dapi.kakao.com")
                        .state(CircuitBreaker.State.CLOSED)
                        .build(),
                CircuitBreakerResponse.builder()
                        .name("openapi.naver.com")
                        .state(CircuitBreaker.State.CLOSED)
                        .build());
        given(service.all()).willReturn(response);

        // expect
        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/locations/circuit/all")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("circuit-breaker-all",
                        ApiDocumentsUtil.getDocumentRequest(),
                        ApiDocumentsUtil.getDocumentResponse(),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getCode()),
                                fieldWithPath("message").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description(ApiResponseCode.SUCCESS.getDefaultMessage()),
                                fieldWithPath("data[]").type(JsonFieldType.ARRAY).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("결과"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("이름"),
                                fieldWithPath("data[].state").type(JsonFieldType.STRING).attributes(ApiDocumentFormatGenerator.getEmptyFormat()).description("상태")
                        )
                ));
    }
}