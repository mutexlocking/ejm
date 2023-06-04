package com.company.ejm.code;

import com.company.ejm.common.BaseEntity;
import com.company.ejm.common.enums.Status;
import com.company.ejm.group.CommonCodeGroup;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "common_code")
public class CommonCode extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_code_id")
    private Long id;

    private String name;

    private Integer value;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name = "common_code_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CommonCodeGroup commonCodeGroup;

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

