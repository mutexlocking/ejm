package com.company.ejm.code;

import com.company.ejm.common.BaseEntity;
import com.company.ejm.common.enums.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

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

