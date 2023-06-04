package com.company.ejm.code.dto.response.paging;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
