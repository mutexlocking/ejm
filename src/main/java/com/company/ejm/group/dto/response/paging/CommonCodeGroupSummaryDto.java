package com.company.ejm.group.dto.response.paging;

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
}
