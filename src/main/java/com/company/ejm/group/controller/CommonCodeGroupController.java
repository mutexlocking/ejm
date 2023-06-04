package com.company.ejm.group.controller;

import com.company.ejm.group.service.CommonCodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommonCodeGroupController {

    private final CommonCodeGroupService commonCodeGroupService;
}
