package com.company.ejm.group.repository;

import com.company.ejm.common.enums.Status;
import com.company.ejm.group.CommonCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeGroupRepository extends JpaRepository<CommonCodeGroup, Long> , CommonCodeGroupRepositoryCustom {

    boolean existsByNameAndStatus(String name, Status status);

    boolean existsByValueAndStatus(Integer value, Status status);

    Optional<CommonCodeGroup> findByIdAndStatus(Long id, Status status);

    Optional<CommonCodeGroup> findByNameAndStatus(String name, Status status);

    Optional<CommonCodeGroup> findByValueAndStatus(Integer value, Status status);

    long count();
}
