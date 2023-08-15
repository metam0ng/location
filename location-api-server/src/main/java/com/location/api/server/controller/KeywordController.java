package com.location.api.server.controller;

import com.location.api.server.dto.response.KeywordResponse;
import com.location.api.server.service.query.KeywordQueryService;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/locations")
public class KeywordController {

    private final KeywordQueryService keywordQueryService;

    /**
     * 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드 목록을 제공한다
     * @return
     */
    @GetMapping("/keywords")
    public ResponseEntity<ApiResponse<List<KeywordResponse>>> findKeywords() {
        List<KeywordResponse> response = keywordQueryService.findKeywordTop10();
        ApiResponse<List<KeywordResponse>> apiResponse = ApiResponseGenerator.success(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
