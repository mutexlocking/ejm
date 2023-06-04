package com.company.ejm.group.dto.response;

import com.company.ejm.group.CommonCodeGroup;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeGroupBaseDto {

    private Long id;

    public static CommonCodeGroupBaseDto toDto(CommonCodeGroup commonCodeGroup) {

        return CommonCodeGroupBaseDto.builder()
                                     .id(commonCodeGroup.getId())
                                     .build();
    }
}
