package com.company.ejm.code.repository;

import com.company.ejm.code.dto.response.paging.CommonCodeSummaryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonCodeRepositoryCustom {

    List<CommonCodeSummaryDto> getCommonCodeList(Pageable pageable);
}
