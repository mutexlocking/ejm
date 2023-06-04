package com.company.ejm.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseStatus {

    /**
     * 1000 : 요청 성공
     * */
    OK(true, HttpStatus.OK, 1000, "요청에 성공하였습니다."),
    CREATED(true, HttpStatus.CREATED,1001, "요청에 성공하여, 리소스가 생성되었습니다."),

    VALIDATION_FAIL(false, HttpStatus.BAD_REQUEST,2000, "요청한 값의 검증 로직에서 오류가 발견되었습니다.");


    private final Boolean isSuccess;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    /**
     * ApiResponseStatus에서 각 해당하는 코드를 생성자로 맵핑
     * [열겨형의 생성자 - 반드시 private]
     * <이렇게 열거형 생성자를 정의하면 - 열거형 값의 선언시 , 소괄호를 통해 생성자에 인자를 전달할 수 있음 >
     *     : 그렇게 되면 결과적으로는 열거형 타입의 (열거형도 class) 객체가 생성되고 - 그 객체의 필드가 소괄호에 인자로 전달한 값 대로 초기화되는것!
     * */
    private ApiResponseStatus(boolean isSuccess, HttpStatus httpStatus, int code, String message){
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}
