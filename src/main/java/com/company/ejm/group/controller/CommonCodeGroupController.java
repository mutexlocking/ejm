package com.company.ejm.group.controller;

import com.company.ejm.common.response.ApiResponse;
import com.company.ejm.group.dto.request.CommonCodeGroupRequestDto;
import com.company.ejm.group.dto.response.CommonCodeGroupBaseDto;
import com.company.ejm.group.dto.response.CommonCodeGroupDetailDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupPagingDto;
import com.company.ejm.group.service.CommonCodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ApiResponse<CommonCodeGroupDetailDto> getCommonCodeGroupById(@PathVariable Long groupId) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroupById(groupId));
    }


    /** [이름으로 조회] */
    @GetMapping(value = "/common-code-groups", params = "name")
    public ApiResponse<CommonCodeGroupDetailDto> getCommonCodeGroupByName(@RequestParam String name) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroupByName(name));
    }

    /** [코드그룹값으로 조회] */
    @GetMapping(value = "/common-code-groups", params = "value")
    public ApiResponse<CommonCodeGroupDetailDto> getCommonCodeGroupByValue(@RequestParam Integer value) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroupByValue(value));
    }


    /**
     * [API 2_2.] : CommonCodeGroup 전체 조회
     * */
    @GetMapping("/common-code-groups")
    public ApiResponse<CommonCodeGroupPagingDto> getCommonCodeGroupList(@PageableDefault(value = 0, size = 20) Pageable pageable) {

        return ApiResponse.success(OK, commonCodeGroupService.getCommonCodeGroupList(pageable));
    }

    /**
     * [API 3.] : CommonCodeGroup 수정
     * */
    @PatchMapping("/common-code-groups/{groupId}")
    public ApiResponse<CommonCodeGroupBaseDto> editCommonCodeGroup(@PathVariable Long groupId, @Validated @RequestBody CommonCodeGroupRequestDto commonCodeGroupRequestDto) {

        return ApiResponse.success(OK, commonCodeGroupService.edit(groupId,
                                                                                    commonCodeGroupRequestDto.getName(),
                                                                                    commonCodeGroupRequestDto.getValue(),
                                                                                    commonCodeGroupRequestDto.getDescription()));
    }

    /**
     * [API 4.] : CommonCodeGroup 삭제
     * */
    @DeleteMapping("/common-code-groups/{groupId}")
    public ApiResponse removeCommonCodeGroup(@PathVariable Long groupId) {

        commonCodeGroupService.remove(groupId);
        return ApiResponse.success(OK);
    }
}

