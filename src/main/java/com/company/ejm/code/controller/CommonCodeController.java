package com.company.ejm.code.controller;

import com.company.ejm.code.dto.request.CommonCodeRequestDto;
import com.company.ejm.code.dto.response.CommonCodeBaseDto;
import com.company.ejm.code.dto.response.CommonCodeDetailDto;
import com.company.ejm.code.dto.response.paging.CommonCodePagingDto;
import com.company.ejm.code.service.CommonCodeService;
import com.company.ejm.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * [API 6_2.] : CommonCode 전체 조회
     * */
    @GetMapping("/common-codes")
    public ApiResponse<CommonCodePagingDto> getCommonCodeList(@PageableDefault(value = 0, size = 20) Pageable pageable) {

        return ApiResponse.success(OK, commonCodeService.getCommonCodeList(pageable));
    }

    /**
     * [API 7.] : CommonCode 수정
     * */
    @PatchMapping("/common-codes/{codeId}")
    public ApiResponse<CommonCodeBaseDto> editCommonCode(@PathVariable Long codeId, @Validated @RequestBody CommonCodeRequestDto commonCodeRequestDto) {

        return ApiResponse.success(OK, commonCodeService.edit(codeId, commonCodeRequestDto.getName(), commonCodeRequestDto.getValue(), commonCodeRequestDto.getDescription()));
    }

    /**
     * [API 8.] : CommonCode 삭제
     * */
    @DeleteMapping("/common-codes/{codeId}")
    public ApiResponse removeCommonCode(@PathVariable Long codeId) {

        commonCodeService.remove(codeId);
        return ApiResponse.success(OK);
    }
}
