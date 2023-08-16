package com.location.api.server.search.controller;

import com.location.api.server.search.dto.response.SearchResponse;
import com.location.api.server.search.service.query.SearchQueryService;
import com.location.common.response.ApiResponse;
import com.location.common.response.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@Validated
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
    public ResponseEntity<ApiResponse<List<SearchResponse>>> searchLocationByKeyword(@RequestParam @NotBlank @Size(max = 20) String keyword) {
        List<SearchResponse> response = searchQueryService.searchLocationByKeyword(keyword);
        ApiResponse<List<SearchResponse>> apiResponse = ApiResponseGenerator.success(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
