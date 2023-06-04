package com.company.ejm.group.dto.response.paging;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeGroupPagingDto {

    private List<CommonCodeGroupSummaryDto> summaryDtoList = new ArrayList<>();
    private long totalCount;
}
