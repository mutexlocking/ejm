package com.company.ejm.code.dto.response.paging;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodePagingDto {

    private List<CommonCodeSummaryDto> summaryDtoList = new ArrayList<>();
    private long totalCount;
}
