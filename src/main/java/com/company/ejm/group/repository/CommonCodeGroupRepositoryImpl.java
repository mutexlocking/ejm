package com.company.ejm.group.repository;

import com.company.ejm.common.enums.Status;
import com.company.ejm.group.dto.response.paging.CommonCodeGroupSummaryDto;
import com.company.ejm.group.dto.response.paging.QCommonCodeGroupSummaryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.company.ejm.group.QCommonCodeGroup.commonCodeGroup;
import static com.querydsl.core.types.ExpressionUtils.count;

public class CommonCodeGroupRepositoryImpl implements CommonCodeGroupRepositoryCustom {

    private JPAQueryFactory queryFactory;


    public CommonCodeGroupRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CommonCodeGroupSummaryDto> findCommonCodeGroupList(Pageable pageable) {

        return queryFactory
                .select(new QCommonCodeGroupSummaryDto(commonCodeGroup.id, commonCodeGroup.name, commonCodeGroup.value))
                .from(commonCodeGroup)
                .where(commonCodeGroup.status.eq(Status.ACTIVE))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(commonCodeGroup.value.asc())
                .fetch();
    }
}
