package com.company.ejm.code.service;

import com.company.ejm.code.dto.response.CommonCodeBaseDto;
import com.company.ejm.code.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    /**
     * [공통코드 등록 서비스]
     * */
    @Transactional
    public CommonCodeBaseDto register(String name, Integer value, String description) {

    }
}
