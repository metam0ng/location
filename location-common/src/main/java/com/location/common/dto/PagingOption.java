package com.location.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingOption {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    public static final PagingOption DEFAULT = new PagingOption();

    /** "한페이지당 보여줄 건수", example = "20" */
    private int pageSize;
    
    /** "현재 페이지", example = "1" */
    private int pageNumber;

    public PagingOption() {
        this.pageSize = 20;
        this.pageNumber = 1;
    }

    public PagingOption(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

}
