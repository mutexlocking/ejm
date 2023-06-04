package com.company.ejm.group.dto.response.paging;

import com.company.ejm.group.CommonCodeGroup;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
public class CommonCodeGroupSummaryDto {

    private Long id;
    private String name;
    private Integer value;

    @QueryProjection
    public CommonCodeGroupSummaryDto(Long id, String name, Integer value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static CommonCodeGroupSummaryDto toDto(CommonCodeGroup commonCodeGroup) {
        return CommonCodeGroupSummaryDto.builder()
                                        .id(commonCodeGroup.getId())
                                        .name(commonCodeGroup.getName())
                                        .value(commonCodeGroup.getValue())
                                        .build();
    }
}
