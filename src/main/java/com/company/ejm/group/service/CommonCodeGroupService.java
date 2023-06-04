package com.company.ejm.group.service;

import com.company.ejm.common.enums.Status;
import com.company.ejm.common.response.ApiException;
import com.company.ejm.common.response.ApiResponseStatus;
import com.company.ejm.group.CommonCodeGroup;
import com.company.ejm.group.dto.response.CommonCodeGroupBaseDto;
import com.company.ejm.group.dto.response.CommonCodeGroupDetailDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupPagingDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupSummaryDto;
import com.company.ejm.group.repository.CommonCodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeGroupService {

    private final CommonCodeGroupRepository commonCodeGroupRepository;

    /**
     *  [공통코드그룹을 등록하는 서비스]
     */
    @Transactional
    public CommonCodeGroupBaseDto register(String name, Integer value, String description) {

        //1. 해당 name과 , codeGroupValue를 가진 CommonCodeGroup Entity가 존재하는지 -> 각각 유효성 검사
        // (동시에 검사하면 안되고 , 각각 따로 따로 검사해야 , name과 value 각각의 unique 성이 보장됨)
        if (commonCodeGroupRepository.existsByName(name)) {

            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_GROUP, "공통코드그룹 생성 시점 : 해당 코드그룹명이 이미 사용중 입니다.");
        }

        if (commonCodeGroupRepository.existsByValue(value)) {

            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_GROUP, "공통코드그룹 생성 시점 : 해당 코드그룹값이 이미 사용중 입니다.");
        }


        //2. 생성 후 저장
        CommonCodeGroup commonCodeGroup = CommonCodeGroup.builder()
                                                         .name(name)
                                                         .value(value)
                                                         .description(description)
                                                         .status(Status.ACTIVE)
                                                         .build();
        commonCodeGroupRepository.save(commonCodeGroup);

        //3. 응답
        return CommonCodeGroupBaseDto.toDto(commonCodeGroup);
    }

    /** --------------------------------------------------------------------------------------------------------------*/

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : id로 조회
     */

    public CommonCodeGroupDetailDto getCommonCodeGroup(Long groupId) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 이름으로 조회
     */

    public CommonCodeGroupDetailDto getCommonCodeGroup(String name) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByNameAndStatus(name, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 코드그룹값으로 조회
     */
    public CommonCodeGroupDetailDto getCommonCodeGroup(int value) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByValueAndStatus(value, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /** --------------------------------------------------------------------------------------------------------------*/


    /**
     *  [공통코드그룹들을 모두 조회하는 서비스]
     *  : 페이징 처리
     */
    public CommonCodeGroupPagingDto getCommonCodeGroupList(Pageable pageable) {

        List<CommonCodeGroupSummaryDto> summaryDtoList = commonCodeGroupRepository.findCommonCodeGroupList(pageable);
        long totalCount = commonCodeGroupRepository.countByStatus(Status.ACTIVE);

        return CommonCodeGroupPagingDto.builder()
                                       .summaryDtoList(summaryDtoList)
                                       .totalCount(totalCount)
                                       .build();
    }

    /** --------------------------------------------------------------------------------------------------------------*/


    /**
     * [공통코드그룹 값을 수정하는 서비스]
     * */
    @Transactional
    public CommonCodeGroupBaseDto editCommonCodeGroup(Long groupId, String name, Integer value, String description) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        commonCodeGroup.changeName(name);
        commonCodeGroup.changeValue(value);
        commonCodeGroup.changeDescription(description);

        return CommonCodeGroupBaseDto.toDto(commonCodeGroup);
    }

    /** --------------------------------------------------------------------------------------------------------------*/

    /**
     * [공통코드그룹 값을 논리적으로 삭제하는 서비스]
     * */
    @Transactional
    public void removeCommonCodeGroup(Long groupId) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        commonCodeGroup.changeStatus(Status.INACTIVE);
    }
}
