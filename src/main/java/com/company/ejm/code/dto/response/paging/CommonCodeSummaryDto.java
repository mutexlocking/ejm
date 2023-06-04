package com.company.ejm.code.dto.response.paging;

import com.company.ejm.code.CommonCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonCodeSummaryDto {

    private Long id;
    private String name;
    private Integer value;

    @QueryProjection
    public CommonCodeSummaryDto(Long id, String name, Integer value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static CommonCodeSummaryDto toDto(CommonCode commonCode) {

        return CommonCodeSummaryDto.builder()
                                   .id(commonCode.getId())
                                   .name(commonCode.getName())
                                   .value(commonCode.getValue())
                                   .build();
    }
}
