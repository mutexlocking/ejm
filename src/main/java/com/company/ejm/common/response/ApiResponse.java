package com.company.ejm.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // Json으로 응답이 나갈 때 - null인 필드는(CLASS LEVEL에 붙었으니) 응답으로 포함시키지 않는 어노테이션
public class ApiResponse<T> {

    private final Boolean isSuccess; // null인 경우를 막고자 primitive 타입으로 설정함

    private final HttpStatus httpStatus;

    private final int code; // null인 경우를 막고자 primitive 타입으로 설정함

    private final String message;

    private T result;

    private T invalidInput;

    /**
     * [정적 메서드 팩토리 패턴]
     * :생성자를 private으로 정의하므로 써 , 생성자를 통한 객체 생성을 막고 , 대신 static 생성메서드를 통한 객체 생성을 유도함
     * */

    // (result 필드는 어차피 null 초기값이 그대로 있어서 - Json으로 변환시 나가지 않을 것)
    @JsonPropertyOrder({"isSuccess", "httpStatus", "code", "message"})
    private ApiResponse(boolean isSuccess, HttpStatus httpStatus, int code, String message) {
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @JsonPropertyOrder({"isSuccess", "httpStatus", "code", "message", "result"})
    private ApiResponse(boolean isSuccess, HttpStatus httpStatus, int code, String message, T result) {
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @JsonPropertyOrder({"isSuccess", "httpStatus", "code", "message", "invalidInput"})
    private ApiResponse(ApiResponseStatus status, T invalidInput) {
        this.isSuccess = status.getIsSuccess();
        this.httpStatus = status.getHttpStatus();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.invalidInput = invalidInput;
    }

    /**
     * [API 성공시 나가는 응답]
     * : 응답 결과가 없는 경우
     * */

    public static ApiResponse success(ApiResponseStatus status){
        return new ApiResponse(status.getIsSuccess(), status.getHttpStatus(), status.getCode(), status.getMessage());
    }

    /**
     * [API 성공시 나가는 응답]
     * : 응답 결과가 있는 경우
     * */

    public static <T> ApiResponse<T> success(ApiResponseStatus status, T result){
        return new ApiResponse<>(status.getIsSuccess(), status.getHttpStatus(), status.getCode(), status.getMessage(), result);
    }

    /**
     *  [API 실패시 나가는 응답]
     *  : 응답 결과가 없는 경우
     * */
    public static ApiResponse fail(ApiResponseStatus status){
        return new ApiResponse<>(status.getIsSuccess(), status.getHttpStatus(), status.getCode(), status.getMessage());
    }

    /**
     *  [API 실패시 나가는 응답]
     *  : 어떤 입력이 들어와서 실패 했는지 , 그 응답을 함께 보내줌
     *  */
    public static <T> ApiResponse<T> failWithInput(ApiResponseStatus status, T invalidInput){
        return new ApiResponse<>(status, invalidInput);
    }

    /**
     *  [API 실패시 나가는 응답]
     *  :  Bean Validation 에 의한 검증 오류 시 , 그 결과를 넣을 수 있도록 함
     *  */
    public static <T> ApiResponse<T> failBeanValidation(T bindResult){
        return new ApiResponse<>(ApiResponseStatus.VALIDATION_FAIL, bindResult);
    }
}

