package com.location.common.response;

import com.location.common.code.ApiResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;

    private String message;

    private T data;

    ApiResponse(ApiResponseCode apiResponseCode){
        this(apiResponseCode, null, null);
    }

    ApiResponse(ApiResponseCode apiResponseCode, String responseMessage, T data){
        this.code = apiResponseCode.getCode();
        this.message = ( responseMessage == null ? apiResponseCode.getDefaultMessage() : responseMessage );
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
