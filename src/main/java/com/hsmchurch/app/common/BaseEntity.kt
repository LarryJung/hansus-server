package com.hsmchurch.app.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
data class BaseEntity(@CreatedDate
                      var createdAt: LocalDateTime,
                      @LastModifiedDate
                      var updatedAt: LocalDateTime,
                      private var deletedAt: LocalDateTime?) {

    val isDeleted: Boolean = false

    val isUsable: Boolean = !isDeleted

    fun markAsDeleted() {
        deletedAt = LocalDateTime.now()
    }

    fun markAsLive() {
        deletedAt = null
    }


}
