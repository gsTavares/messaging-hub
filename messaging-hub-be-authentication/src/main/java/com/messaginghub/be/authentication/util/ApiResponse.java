package com.messaginghub.be.authentication.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    
    private T data;
    private String message;

}
