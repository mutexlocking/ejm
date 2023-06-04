package com.company.ejm.group.service;

import com.company.ejm.common.enums.Status;
import com.company.ejm.common.response.ApiException;
import com.company.ejm.common.response.ApiResponseStatus;
import com.company.ejm.group.CommonCodeGroup;
import com.company.ejm.group.dto.response.CommonCodeGroupBaseDto;
import com.company.ejm.group.dto.response.CommonCodeGroupResponseDto;
import com.company.ejm.group.repository.CommonCodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (commonCodeGroupRepository.existsByNameAndStatus(name, Status.ACTIVE)) {

            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_GROUP, "공통코드그룹 생성 시점 : 해당 코드그룹명이 이미 사용중 입니다.");
        }

        if (commonCodeGroupRepository.existsByValueAndStatus(value, Status.ACTIVE)) {

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

    public CommonCodeGroupResponseDto getCommonCodeGroup(Long groupId) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupResponseDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 이름으로 조회
     */

    public CommonCodeGroupResponseDto getCommonCodeGroup(String name) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByNameAndStatus(name, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupResponseDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 코드그룹값으로 조회
     */
    public CommonCodeGroupResponseDto getCommonCodeGroup(int value) {

        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByValueAndStatus(value, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        return CommonCodeGroupResponseDto.toDto(commonCodeGroup);
    }
}
