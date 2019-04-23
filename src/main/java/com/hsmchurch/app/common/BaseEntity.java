package com.hsmchurch.app.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt = null;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = null;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = null;

    public void markAsDeleted() {
        deletedAt = LocalDateTime.now();
    }

    public void markAsLive() {
        deletedAt = null;
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public boolean isUsable() {
        return !isDeleted();
    }

}
