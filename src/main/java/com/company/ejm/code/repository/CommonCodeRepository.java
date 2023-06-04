package com.company.ejm.code.repository;

import com.company.ejm.code.CommonCode;
import com.company.ejm.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {

    boolean existsByName(String name);

    boolean existsByValue(Integer value);

    Optional<CommonCode> findByIdAndStatus(Long id, Status status);;

    Optional<CommonCode> findByNameAndStatus(String name, Status status);

    Optional<CommonCode> findByValueAndStatus(Integer value, Status status);
}
