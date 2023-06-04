package com.company.ejm.code.repository;

import com.company.ejm.code.CommonCode;
import com.company.ejm.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {

    boolean existsByName(String name);

    boolean existsByValue(Integer value);
}
