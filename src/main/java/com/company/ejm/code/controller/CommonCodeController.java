package com.company.ejm.code.controller;

import com.company.ejm.code.dto.request.CommonCodeRequestDto;
import com.company.ejm.code.dto.response.CommonCodeBaseDto;
import com.company.ejm.code.dto.response.CommonCodeDetailDto;
import com.company.ejm.code.service.CommonCodeService;
import com.company.ejm.common.response.ApiResponse;
import com.company.ejm.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.company.ejm.common.response.ApiResponseStatus.CREATED;
import static com.company.ejm.common.response.ApiResponseStatus.OK;

@RestController
@RequiredArgsConstructor
public class CommonCodeController {

    private final CommonCodeService commonCodeService;


    /**
     * [API 5.] : CommonCode 등록
     * */
    @PostMapping("/common-codes")
    public ApiResponse<CommonCodeBaseDto> registerCode(@Validated @RequestBody CommonCodeRequestDto commonCodeRequestDto) {

        return ApiResponse.success(CREATED, commonCodeService.register(commonCodeRequestDto.getName(), commonCodeRequestDto.getValue(), commonCodeRequestDto.getDescription()));
    }

    /**
     * [API 6_1.] : CommonCode 단건 조회
     * */

    /** [id로 조회] */
    @GetMapping("/common-codes/{codeId}")
    public ApiResponse<CommonCodeDetailDto> getCommonCodeById(@PathVariable Long codeId) {

        return ApiResponse.success(OK, commonCodeService.getCommonCodeById(codeId));
    }

    /** [이름으로 조회] */
    @GetMapping(value = "/common-codes", params = "name")
    public ApiResponse<CommonCodeDetailDto> getCommonCodeByName(@RequestParam String name) {

        return ApiResponse.success(OK, commonCodeService.getCommonCodeByName(name));
    }

    /** [코드값으로 조회] */
    @GetMapping(value = "/common-codes", params = "value")
    public ApiResponse<CommonCodeDetailDto> getCommonCodeByValue(@RequestParam Integer value) {

        return ApiResponse.success(OK, commonCodeService.getCommonCodeByValue(value));
    }
}
