package com.location.api.server.controller;

import com.location.api.server.service.query.SearchQueryService;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/locations")
public class SearchController {

    private final SearchQueryService searchQueryService;

    /**
     * keyword로 장소 조회
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<String>>> searchLocationByKeyword(@RequestParam @NonNull String keyword) {
        List<String> response = searchQueryService.searchLocationByKeyword(keyword);
        ApiResponse<List<String>> apiResponse = ApiResponseGenerator.success(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
