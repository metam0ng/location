package com.location.common.response;

import com.location.common.dto.PagingOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageResponse<T> {

    private int pageSize;

    private int pageNumber;

    private int totalPageNumber;

    private Long totalSize;

    private List<T> list;

    public PageResponse(int pageSize, int pageNumber, int totalPageNumber, Long totalSize, List<T> list) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPageNumber = totalPageNumber;
        this.totalSize = totalSize;
        this.list = list;
    }

    public static <T> PageResponse <T> empty(PagingOption pagingOption) {
        return new PageResponse<>(pagingOption.getPageSize(), pagingOption.getPageNumber() + 1, 0, 0L, Collections.emptyList());
    }

}
