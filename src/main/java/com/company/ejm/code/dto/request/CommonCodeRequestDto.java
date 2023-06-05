package com.company.ejm.code.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeRequestDto {

    @NotNull
    @Pattern(regexp = "^[가-힣]+$", message = "공통코드그룹의 이름은 한글로 작성되어야 합니다.")
    private String name;

    @NotNull
    @Min(value = 10000, message = "공통코드그룹의 코드그룹값의 최솟값은 10000 이어야 합니다.")
    @Max(value = 99999, message = "공통코드그룹의 코드그룹값의 최댓값은 99999 이어야 합니다.")
    private Integer value;

    @NotNull
    @Size(min = 2, max = 200, message = "공통코드그룹의 상세내용은 2글자 이상 200글자 이하여야 합니다.")
    private String description;
}
