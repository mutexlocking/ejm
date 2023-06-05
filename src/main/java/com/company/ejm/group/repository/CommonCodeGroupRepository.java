package com.company.ejm.group.repository;

import com.company.ejm.common.enums.Status;
import com.company.ejm.group.CommonCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommonCodeGroupRepository extends JpaRepository<CommonCodeGroup, Long> , CommonCodeGroupRepositoryCustom {

    boolean existsByName(String name);

    boolean existsByValue(Integer value);

    boolean existsByNameAndIdIsNot(String name, Long id);

    boolean existsByValueAndIdIsNot(Integer value, Long id);

    boolean existsByValueAndStatus(Integer value, Status status);

    Optional<CommonCodeGroup> findByIdAndStatus(Long id, Status status);

    Optional<CommonCodeGroup> findByNameAndStatus(String name, Status status);

    Optional<CommonCodeGroup> findByValueAndStatus(Integer value, Status status);

    long countByStatus(Status status);

    @Query("select distinct cg from CommonCodeGroup cg join fetch cg.commonCodeList where cg.id=:id and cg.status=:status")
    Optional<CommonCodeGroup> findByIdAndStatusWithCode(@Param("id") Long id, @Param("status") Status status);
}
