package com.company.ejm.code.service;

import com.company.ejm.code.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;
}
