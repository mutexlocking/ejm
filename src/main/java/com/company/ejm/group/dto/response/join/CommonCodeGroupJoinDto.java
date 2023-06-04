package com.company.ejm.group.dto.response.join;

import com.company.ejm.code.dto.response.paging.CommonCodeSummaryDto;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupSummaryDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeGroupJoinDto {

    private CommonCodeGroupSummaryDto commonCodeGroupSummaryDto;
    private List<CommonCodeSummaryDto> commonCodeSummaryDtoList = new ArrayList<>();
}
