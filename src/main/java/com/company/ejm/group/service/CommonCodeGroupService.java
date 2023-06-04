package com.company.ejm.group.service;

import com.company.ejm.code.dto.response.paging.CommonCodeSummaryDto;
import com.company.ejm.common.enums.Status;
import com.company.ejm.common.response.ApiException;
import com.company.ejm.common.response.ApiResponseStatus;
import com.company.ejm.group.CommonCodeGroup;
import com.company.ejm.group.dto.response.CommonCodeGroupBaseDto;
import com.company.ejm.group.dto.response.CommonCodeGroupDetailDto;
import com.company.ejm.group.dto.response.join.CommonCodeGroupJoinDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupPagingDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupSummaryDto;
import com.company.ejm.group.repository.CommonCodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public CommonCodeGroupDetailDto getCommonCodeGroupById(Long groupId) {

        //1. 공통코드그룹의 ID로 -> 유효한 공통코드그룹을 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2. 응답
       return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 이름으로 조회
     */

    public CommonCodeGroupDetailDto getCommonCodeGroupByName(String name) {

        //1. 공통코드그룹의 이름으로 -> 유효한 공통코드그룹을 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByNameAndStatus(name, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2. 응답
        return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /**
     *  [공통코드그룹을 조회하는 서비스]
     *  : 코드그룹값으로 조회
     */
    public CommonCodeGroupDetailDto getCommonCodeGroupByValue(Integer value) {

        //1. 공통코드그룹의 코드값으로 -> 유효한 공통코드그룹을 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByValueAndStatus(value, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 이름으로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2. 응답
        return CommonCodeGroupDetailDto.toDto(commonCodeGroup);
    }

    /** --------------------------------------------------------------------------------------------------------------*/


    /**
     *  [공통코드그룹들을 모두 조회하는 서비스]
     *  : 페이징 처리
     */
    public CommonCodeGroupPagingDto getCommonCodeGroupList(Pageable pageable) {

        //1. 페이징 처리를 수행하면서 , 전체 공통코드그룹을 조회한 후
        List<CommonCodeGroupSummaryDto> summaryDtoList = commonCodeGroupRepository.findCommonCodeGroupList(pageable);

        //2. 그에 대한 totalCount도 조회한 후
        long totalCount = commonCodeGroupRepository.countByStatus(Status.ACTIVE);

        //3. 이들을 응답에 담아 리턴
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
    public CommonCodeGroupBaseDto edit(Long groupId, String name, Integer value, String description) {

        //1. 공통코드그룹 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2.수정할 이름 이미 사용중인지 + 수정할 코드값 이미 사용중인지 유효성 검사
        if (commonCodeGroupRepository.existsByName(name)) {

            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_GROUP, "공통코드그룹 수정 시점 : 해당 코드그룹명이 이미 사용중 입니다.");
        }

        if (commonCodeGroupRepository.existsByValue(value)) {

            throw new ApiException(ApiResponseStatus.ALREADY_EXIST_GROUP, "공통코드그룹 수정 시점 : 해당 코드그룹값이 이미 사용중 입니다.");
       }

        //3. 값 변경
        commonCodeGroup.changeName(name);
        commonCodeGroup.changeValue(value);
        commonCodeGroup.changeDescription(description);

        //4. 응답
        return CommonCodeGroupBaseDto.toDto(commonCodeGroup);
    }

    /** --------------------------------------------------------------------------------------------------------------*/

    /**
     * [공통코드그룹 값을 논리적으로 삭제하는 서비스]
     * */
    @Transactional
    public void remove(Long groupId) {

        //1. 유효한 공통코드그룹을 조회하여
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatus(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2. Status값을 INACTIVE 하게 변경하므로써 -> 논리적 삭제 수행
        commonCodeGroup.changeStatus(Status.INACTIVE);
    }

    /** --------------------------------------------------------------------------------------------------------------*/

    /**
     * [공통코드그룹 조회시 , 연관된 공통코드들을 함께 조회해오는 서비스]
     * */
    public CommonCodeGroupJoinDto getCommonCodeGroupWithCodeList(Long groupId) {

        //1. 해당 groupId에 대한 공통코드그룹을 조회하면서 -> 연관된 공통코드들을 모두 함께 조회
        CommonCodeGroup commonCodeGroup = commonCodeGroupRepository.findByIdAndStatusWithCode(groupId, Status.ACTIVE).orElseThrow(
                () -> {
                    throw new ApiException(ApiResponseStatus.NOT_FOUND_GROUP, "공통코드그룹 ID로 연관된 공통코드들까지 함께 조회 시점 : 유효한 공통코드그룹을 찾을 수 없음");
                }
        );

        //2_1. 공통코드그룹을 SummaryDto로 변환
        CommonCodeGroupSummaryDto commonCodeGroupSummaryDto = CommonCodeGroupSummaryDto.toDto(commonCodeGroup);

        //2_2. 공통코드들을 SummaryDtoList로 변환
        List<CommonCodeSummaryDto> commonCodeSummaryDtoList = commonCodeGroup.getCommonCodeList().stream()
                .map(commonCode -> CommonCodeSummaryDto.toDto(commonCode))
                .collect(Collectors.toList());

        //3. 응답에 모두 담아 리턴
        return CommonCodeGroupJoinDto.builder()
                                     .commonCodeGroupSummaryDto(commonCodeGroupSummaryDto)
                                     .commonCodeSummaryDtoList(commonCodeSummaryDtoList)
                                     .build();
    }
}
