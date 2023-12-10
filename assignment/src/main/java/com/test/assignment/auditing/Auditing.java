package com.test.assignment.auditing;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditing {

    @CreatedDate
    @Column(updatable = false, name = "REG_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt; //작성일시
}
