package com.company.ejm.code.dto.response;

import com.company.ejm.code.CommonCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeBaseDto {

    private Long id;

    public static CommonCodeBaseDto toDto(CommonCode commonCode) {

        return CommonCodeBaseDto.builder()
                                .id(commonCode.getId())
                                .build();
    }
}
