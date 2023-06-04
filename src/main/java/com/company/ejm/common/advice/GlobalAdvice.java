package com.company.ejm.common.advice;

import com.company.ejm.common.response.ApiException;
import com.company.ejm.common.response.ApiResponse;
import com.company.ejm.common.response.ApiResponseStatus;
import com.company.ejm.common.response.validation.ValidationFail;
import com.company.ejm.common.response.validation.ValidationFailForField;
import com.company.ejm.common.response.validation.ValidationFailForObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.company.ejm.common.response.ApiResponseStatus.INVALID_ENUM;

@Slf4j
@RestControllerAdvice
public class GlobalAdvice {


    /**
     * [우리가 정의한 예외 상황 발생시에]
     * -> 따른 실패 응답
     * */
    @ExceptionHandler
    public ApiResponse exHandler(ApiException e){
        log.error("EXCEPTION = {}, INTERNAL_MESSAGE = {}", e.getStatus(), e.getInternalMessage());
        return ApiResponse.fail(e.getStatus());
    }


    /**
     * [필수 쿼리 파라미터가 없을 때 발생하는 예외 처리에 대한]
     * -> 실패 응답
     * */
    @ExceptionHandler
    public ApiResponse missingRequestParamExHandler(MissingServletRequestParameterException e){
        log.error("EXCEPTION = {}, INTERNAL_MESSAGE = {}", e, e.getMessage());
        return ApiResponse.fail(ApiResponseStatus.MISSING_REQUEST_PARAMETER);
    }


    /**
     * [Bean Validation 기능에 따른 검증에서 오류가 존재하여]
     *  -> BindingResult에 FieldError 또는 ObjectError가 있음에도
     *  -> Controller에서 이에 대한 에러 핸들링을 하지 않는 경우  -> BindException이 터진다 -> 그러면 이 ExceptionHandler까지 예외가 올라오고 , 여기서 일괄적으로 처리한다
     *  */
    @ExceptionHandler
    public ApiResponse bindExHandler(BindException e, BindingResult bindingResult){
        log.error("EXCEPTION = {}, INTERNAL_MESSAGE = {}", e, e.getMessage());
        ValidationFail validationFail = makeValidationError(bindingResult);
        return ApiResponse.failBeanValidation(validationFail);

    }

    private ValidationFail makeValidationError(BindingResult bindingResult){
        return ValidationFail.builder()
                .fieldList(bindingResult.getFieldErrors().stream()
                        .map(f -> new ValidationFailForField(f))
                        .collect(Collectors.toList()))
                .objectList(bindingResult.getGlobalErrors().stream()
                        .map(o -> new ValidationFailForObject(o))
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * [정의되지 않은 enum 값이 넘어왔을 떄 터지는 Exception을 처리하는 Exception Handler]
     * */
    @ExceptionHandler
    public ApiResponse inCorrectEnum(HttpMessageNotReadableException e){
        log.error("EXCEPTION = {} , EXCEPTION_MESSAGE = {}, INTERNAL_MESSAGE = {}", INVALID_ENUM, e.getMessage(),"정의하지 않은, 잘못된 enum 값이 요청으로 들어왔습니다.");
        return ApiResponse.fail(INVALID_ENUM);
    }

    /**
     * [설정한 HTTP 메소드와 다른 HTTP 메소드로 요청이 들어온 경우]
     * */
    @ExceptionHandler
    public ApiResponse inCorrectHttpMethod(HttpRequestMethodNotSupportedException e){
        log.error("EXCEPTION = {} , INTERNAL_MESSAGE = {}", e, e.getMessage());
        return ApiResponse.fail(ApiResponseStatus.INCORRECT_HTTP_METHOD);
    }


    /**--------------------------------------------------------------------------------*/
    /**
     *  [그 외 우리가 예상하지 못한 예외가 발생했을 때 잡아주는 처리]
     *  */
    @ExceptionHandler
    public ApiResponse internalServerExHandler(Exception e){
        log.error("EXCEPTION = {}, INTERNAL_MESSAGE = {}", e, e.getMessage());
        return ApiResponse.fail(ApiResponseStatus.INTERNAL_SERVER_ERROR);
    }

}

