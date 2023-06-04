package com.company.ejm.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false, name = "created_at") //createdAt은 최초에 생성될 떄 초기화 된 이후 업데이트 되면 안되는 값 이므로 -> 업데이트를 막아둠
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    /** em.persist() 가 수행되기 전 */
    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    /** dirty checking 으로 update 쿼리가 나가기 전 */
    @PreUpdate
    public void postUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
