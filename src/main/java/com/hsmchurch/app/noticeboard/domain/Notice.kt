package com.hsmchurch.app.noticeboard.domain

import com.hsmchurch.app.common.BaseEntity
import com.hsmchurch.app.common.Feedable
import com.hsmchurch.app.common.HasOwner
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest
import javax.persistence.*

import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "notices")
data class Notice(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Long = -1,

        @Column(name = "title")
        var title: String,

        @Lob
        @Column(name = "content")
        var content: String,

        @Embedded
        var writer: Writer
) : BaseEntity(), HasOwner{

    fun update(noticeUpdateRequest: NoticeUpdateRequest,
               writerId: Long): Notice {
        if (!isOwner(writerId)) throw RuntimeException("올바른 작성자가 아닙니다. writer id: $writerId")
        return Notice(
                title = noticeUpdateRequest.title,
                content = noticeUpdateRequest.content,
                writer = this.writer
        )
    }

    fun checkAndDelete(writerId: Long): Boolean {
        if (isOwner(writerId)) {
            markAsDeleted()
            return true
        }
        return false
    }

    override fun isOwner(writerId: Long): Boolean {
        return this.writer.isYou(writerId)
    }

    companion object {

        fun of(noticeUploadRequest: NoticeUploadRequest, writerId: Long): Notice {
            return Notice(
                    title = noticeUploadRequest.title,
                    content = noticeUploadRequest.content,
                    writer = Writer(writerId))
        }
    }
}
