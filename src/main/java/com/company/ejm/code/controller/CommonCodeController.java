package com.company.ejm.code.controller;

import com.company.ejm.code.dto.request.CommonCodeRequestDto;
import com.company.ejm.code.dto.response.CommonCodeBaseDto;
import com.company.ejm.code.service.CommonCodeService;
import com.company.ejm.common.response.ApiResponse;
import com.company.ejm.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.company.ejm.common.response.ApiResponseStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class CommonCodeController {

    private final CommonCodeService commonCodeService;


    /**
     * [CommonCode 등록]
     * */
    @PostMapping("/common-codes")
    public ApiResponse<CommonCodeBaseDto> registerCode(@Validated @RequestBody CommonCodeRequestDto commonCodeRequestDto) {

        return ApiResponse.success(CREATED, commonCodeService.register(commonCodeRequestDto.getName(), commonCodeRequestDto.getValue(), commonCodeRequestDto.getDescription()));
    }
}
