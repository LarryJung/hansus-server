package com.hsmchurch.app.noticeboard.domain


import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Writer(
        @Column(name = "writer_id") val id: Long
) {

    fun isYou(writerId: Long): Boolean = writerId == this.id

}
