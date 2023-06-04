package com.company.ejm.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.File;

/**
 * [ 1000단위 ] - 오류의 범위
 *  1000 : 요청 성공
 *  2 : Request 오류
 *  3 : Reponse 오류
 *  4 : DB 오류
 *  5 : Server 오류
 *
 * [ 100단위 ] - 오류 도메인
 *  0 : 공통 오류
 *  1 : CommonCodeGroup 오류
 *  2 : CommonCode 오류
 *
 * [10단위] - 오류 HTTP Method
 *  0~19 : Common
 *  20~39 : GET
 *  40~59 : POST
 *  60~79 : PATCH
 *  80~99 : DELETE
 *
 *  [1 단위] - 그외 오류의 고유 식별자
 *          - 순서대로 설정해주면 됨
 *
 */

@Getter
public enum ApiResponseStatus {

    /**
     * 1000 : 요청 성공
     * */
    OK(true, HttpStatus.OK, 1000, "요청에 성공하였습니다."),
    CREATED(true, HttpStatus.CREATED,1001, "요청에 성공하여, 리소스가 생성되었습니다."),

    VALIDATION_FAIL(false, HttpStatus.BAD_REQUEST,2000, "요청한 값의 검증 로직에서 오류가 발견되었습니다."),
    MISSING_REQUEST_PARAMETER(false, HttpStatus.BAD_REQUEST, 2001, "필수 쿼리파라미터 값이 넘어오지 않았습니다."),
    INVALID_HTTP_REQUEST_VALUE(false, HttpStatus.BAD_REQUEST, 2002, "잘못된 HTTP 요청값이 들어왔습니다."),
    INVALID_HTTP_METHOD(false, HttpStatus.BAD_REQUEST, 2003, "잘못 매칭된 HTTP 메소드로 요청이 들어왔습니다."),

    NOT_FOUND_GROUP(false, HttpStatus.BAD_REQUEST, 2102, "해당 공통코드그룹을 찾을 수 없습니다."),
    ALREADY_EXIST_GROUP(false, HttpStatus.BAD_REQUEST, 2141, "해당 이름 또는 코드그룹값을 가진 공통코드그룹이 이미 존재합니다."),

    INVALID_GROUP_VALUE(false, HttpStatus.BAD_REQUEST, 2101, "유효하지 않은 공통코드그룹 코드값 입니다."),
    NOT_FOUND_CODE(false, HttpStatus.BAD_REQUEST, 2202, "해당 공통코드를 찾을 수 없습니다."),
    ALREADY_EXIST_CODE(false, HttpStatus.BAD_REQUEST, 2241, "해당 이름 또는 코드값을 가진 공통코드가 이미 존재합니다."),
    SAME_GROUP_AND_CODE_NAME(false, HttpStatus.BAD_REQUEST, 2242, "해당 이름을 사용하는 공통코드그룹이 이미 존재합니다."),



    INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, 5000, "예상하지 못한 예외가 발생하였습니다.");



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
