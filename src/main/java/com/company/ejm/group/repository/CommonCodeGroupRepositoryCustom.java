package com.company.ejm.group.repository;

import com.company.ejm.group.dto.response.paging.CommonCodeGroupSummaryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonCodeGroupRepositoryCustom {

    List<CommonCodeGroupSummaryDto> findCommonCodeGroupList(Pageable pageable);
}
