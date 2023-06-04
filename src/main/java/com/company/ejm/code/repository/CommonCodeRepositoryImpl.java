package com.company.ejm.code.repository;

import com.company.ejm.code.dto.response.paging.CommonCodeSummaryDto;
import com.company.ejm.code.dto.response.paging.QCommonCodeSummaryDto;
import com.company.ejm.common.enums.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.company.ejm.code.QCommonCode.commonCode;

public class CommonCodeRepositoryImpl implements CommonCodeRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public CommonCodeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CommonCodeSummaryDto> getCommonCodeList(Pageable pageable) {

        return queryFactory.select(new QCommonCodeSummaryDto(commonCode.id, commonCode.name, commonCode.value))
                           .from(commonCode)
                           .where(commonCode.status.eq(Status.ACTIVE))
                           .offset(pageable.getOffset())
                           .limit(pageable.getPageSize())
                           .orderBy(commonCode.value.asc())
                           .fetch();
    }
}
