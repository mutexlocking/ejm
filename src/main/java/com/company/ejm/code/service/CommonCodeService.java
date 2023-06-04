package com.company.ejm.code.service;

import com.company.ejm.code.CommonCode;
import com.company.ejm.code.dto.response.CommonCodeBaseDto;
import com.company.ejm.code.dto.response.CommonCodeDetailDto;
import com.company.ejm.code.dto.response.paging.CommonCodePagingDto;
import com.company.ejm.code.dto.response.paging.CommonCodeSummaryDto;
import com.company.ejm.code.repository.CommonCodeRepository;
import com.company.ejm.common.enums.Status;
import com.company.ejm.common.response.ApiException;
import com.company.ejm.common.response.ApiResponseStatus;
import com.company.ejm.group.CommonCodeGroup;
import com.company.ejm.group.repository.CommonCodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;
    private final CommonCodeGroupRepository commonCodeGroupRepository;

    /**
     * [공통코드 등록 서비스]
     * */
    @Transactional
    public CommonCodeBaseDto register(String name, Integer value, String description) {

        //1. 해당 이름 또는 코드값을 가진 유효한 공통코드가 이미 존재하는 유효성 검사
        // (마찬가지로 , 이름과 / 코드값을 각각 따로 검사해 주어야 함)

        /** 단, 입력한 공통코드값에 포함된 공통코드 그룹에 대한 정보가 유효한지 먼저 검사해줘야 함*/
        int groupValue = value / 1000;
        if (!commonCodeGroupRepository.existsByValueAndStatus(groupValue, Status.ACTIVE)) {
            throw new ApiException(ApiResponseStatus.INVALID_GROUP_VALUE, "공통코드 생성 시점 : 해당 코드값의 공통코드그룹값 정보가 유효하지 않습니다.");
        }

        /** 또한, 공통코드 이름이 - 공통코드그룹 이름과 겹치지 않는지 검사 (일반적으로 겹치는 것을 지양한다고 함)*/
        if (commonCodeGroupRepository.existsByName(name)) {
            throw new ApiException(ApiResponseStatus.SAME_GROUP_AND_CODE_NAME, "공통코드 생성 시점 : 해당 코드명이, 다른 공통코드그룹 이름과 겹칩니다.");
        }

        if (commonCodeRepository.existsByName(name)) {
            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_CODE, "공통코드 생성 시점 : 해당 코드명이 이미 사용중 입니다.");
        }

        if (commonCodeRepository.existsByValue(value)) {
            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_CODE, "공통코드 생성 시점 : 해당 코드값이 이미 사용중 입니다.");
        }

        //2. 넘어온 정보를 가지고 공통코드 생성 후 저장

        //2_1. 일단 groupValue를 가지고 먼저 유효한 공통코드그룹을 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByValueAndStatus(groupValue, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드 생성 시점 : 해당 코드값으로 유효한 공통코드그룹을 조회할 수 없습니다.");
                }
        );

        //2_2. 이후 공통코드를 생성하여 저장
        CommonCode commonCode = CommonCode.builder()
                                          .name(name)
                                          .value(value)
                                          .description(description)
                                          .status(Status.ACTIVE)
                                          .commonCodeGroup(commonCodeGroup)
                                          .build();

        commonCodeRepository.save(commonCode);

        //3. 응답
        return CommonCodeBaseDto.toDto(commonCode);
    }

    /**---------------------------------------------------------------------------------------------------------------*/

    /**
     * [id로 공통코드 조회 서비스]
     * */
    public CommonCodeDetailDto getCommonCodeById(Long codeId) {

        CommonCode commonCode = commonCodeRepository.findByIdAndStatus(codeId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_CODE, "공통코드 id로 조회 시점 : 유효한 공통코드를 찾을 수 없음");
                }
        );

        return CommonCodeDetailDto.toDto(commonCode);
    }

    /**
     * [이름으로 공통코드 조회 서비스]
     * */
    public CommonCodeDetailDto getCommonCodeByName(String name) {

        CommonCode commonCode = commonCodeRepository.findByNameAndStatus(name, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_CODE, "공통코드 이름으로 조회 시점 : 유효한 공통코드를 찾을 수 없음");
                }
        );

        return CommonCodeDetailDto.toDto(commonCode);
    }

    /**
     * [코드값으로 공통코드 조회 서비스]
     * */
    public CommonCodeDetailDto getCommonCodeByValue(Integer value) {

        CommonCode commonCode = commonCodeRepository.findByValueAndStatus(value, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_CODE, "공통코드 코드값으로 조회 시점 : 유효한 공통코드를 찾을 수 없음");
                }
        );

        return CommonCodeDetailDto.toDto(commonCode);
    }

    /**---------------------------------------------------------------------------------------------------------------*/

    /**
     * [공통코드들을 모두 조회하는 서비스]
     * : 페이징 처리
     * */
    public CommonCodePagingDto getCommonCodeList(Pageable pageable) {

        List<CommonCodeSummaryDto> summaryDtoList = commonCodeRepository.getCommonCodeList(pageable);
        long totalCount = commonCodeRepository.countByStatus(Status.ACTIVE);

        return CommonCodePagingDto.builder()
                                  .summaryDtoList(summaryDtoList)
                                  .totalCount(totalCount)
                                  .build();
    }

    /**---------------------------------------------------------------------------------------------------------------*/
}
