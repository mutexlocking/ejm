package com.company.ejm.group.dto.response;

import com.company.ejm.group.CommonCodeGroup;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeGroupResponseDto {

    private Long id;
    private String name;
    private Integer value;
    private String description;

    public static CommonCodeGroupResponseDto toDto(CommonCodeGroup commonCodeGroup) {

        return CommonCodeGroupResponseDto.builder()
                                         .id(commonCodeGroup.getId())
                                         .name(commonCodeGroup.getName())
                                         .value(commonCodeGroup.getValue())
                                         .description(commonCodeGroup.getDescription())
                                         .build();
    }
}
