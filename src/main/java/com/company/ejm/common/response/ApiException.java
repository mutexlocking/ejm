package com.company.ejm.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class ApiException extends RuntimeException{

    private ApiResponseStatus status;
    private String internalMessage;

    public ApiException(ApiResponseStatus status, String internalMessage){
        this.status = status;
        this.internalMessage = internalMessage;
    }
}