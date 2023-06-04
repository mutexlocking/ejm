package com.company.ejm.group.controller;

import com.company.ejm.common.response.ApiResponse;
import com.company.ejm.group.dto.request.CommonCodeGroupRequestDto;
import com.company.ejm.group.dto.response.CommonCodeGroupBaseDto;
import com.company.ejm.group.dto.response.CommonCodeGroupResponseDto;
import com.company.ejm.group.service.CommonCodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.company.ejm.common.response.ApiResponseStatus.CREATED;
import static com.company.ejm.common.response.ApiResponseStatus.OK;

@RestController
@RequiredArgsConstructor
public class CommonCodeGroupController {

    private final CommonCodeGroupService commonCodeGroupService;

    /**
     * [API 1.] : CommonCodeGroup 등록
     * */
    @PostMapping("/common-code-groups")
    public ApiResponse<CommonCodeGroupBaseDto> registerGroup(@Validated @RequestBody CommonCodeGroupRequestDto commonCodeGroupRequestDto) {

        return ApiResponse.success(CREATED, commonCodeGroupService.register(commonCodeGroupRequestDto.getName(),
                                                                            commonCodeGroupRequestDto.getValue(),
                                                                            commonCodeGroupRequestDto.getDescription()));
    }


    /**
     * [API 2_1.] : CommonCodeGroup 단건 조회
     * */

    /** [id로 조회] */
    @GetMapping("/common-code-groups/{groupId}")
    public ApiResponse<CommonCodeGroupResponseDto> getCommonCodeGroupById(@PathVariable Long groupId) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroup(groupId));
    }


    /** [이름으로 조회] */
    @GetMapping(value = "/common-code-groups", params = "name")
    public ApiResponse<CommonCodeGroupResponseDto> getCommonCodeGroupByName(@RequestParam String name) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroup(name));
    }

    /** [코드그룹값으로 조회] */
    @GetMapping(value = "/common-code-groups", params = "value")
    public ApiResponse<CommonCodeGroupResponseDto> getCommonCodeGroupByValue(@RequestParam int value) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroup(value));
    }


    /**
     * [API 2_2.] : CommonCodeGroup 전체 조회
     * */


    /**
     * [API 3.] : CommonCodeGroup 수정
     * */

    /**
     * [API 4.] : CommonCodeGroup 삭제
     * */
}

