package com.company.ejm.group;

import com.company.ejm.code.CommonCode;
import com.company.ejm.common.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "common_code_group")
public class CommonCodeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_code_group_id")
    private Long id;

    private String name;

    private Integer value;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "commonCodeGroup")
    private List<CommonCode> commonCodeList = new ArrayList<>();

    /**
     * [변경 메서드]
     * */
    public void changeName(String name) {
        this.name = name;
    }

    public void changeValue(Integer value) {
        this.value = value;
    }
}
