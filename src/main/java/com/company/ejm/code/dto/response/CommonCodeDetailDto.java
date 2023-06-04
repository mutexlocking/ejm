package com.company.ejm.code.dto.response;

import com.company.ejm.code.CommonCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeDetailDto {

    private Long id;
    private String name;
    private Integer value;
    private String description;

    public static CommonCodeDetailDto toDto(CommonCode commonCode) {

        return CommonCodeDetailDto.builder()
                                  .id(commonCode.getId())
                                  .name(commonCode.getName())
                                  .value(commonCode.getValue())
                                  .description(commonCode.getDescription())
                                  .build();
    }
}
