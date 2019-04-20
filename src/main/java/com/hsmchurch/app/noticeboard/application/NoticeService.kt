package com.hsmchurch.app.noticeboard.application

import com.hsmchurch.app.common.exceptions.NotFoundException
import com.hsmchurch.app.common.support.CrudStringFormat.DELETE_FAIL
import com.hsmchurch.app.common.support.logger
import com.hsmchurch.app.noticeboard.domain.Notice
import com.hsmchurch.app.noticeboard.domain.NoticeRepository
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class NoticeService constructor(
        private val noticeRepository: NoticeRepository
) {
    companion object {
        private val ENTITY_NAME = "게시글"
        private val log = logger()
    }

    fun findById(noticeId: Long): Notice = noticeRepository.findByIdOrNull(noticeId)
            ?: throw NotFoundException(ENTITY_NAME, noticeId)

    fun findById2(noticeId: Long): NoticeResponse = noticeRepository.findOneWithWriterName(noticeId)
            ?: throw throw NotFoundException(ENTITY_NAME, noticeId)

    fun findListAll(pageable: Pageable): Page<NoticeResponse?> = noticeRepository.findListAll(pageable)

    fun upload(noticeUploadRequest: NoticeUploadRequest,
               writerId: Long): Notice = noticeRepository.save(Notice.of(noticeUploadRequest, writerId))

    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest,
                     boardId: Long,
                     writerId: Long): Notice {
        val notice: Notice = findById(boardId).update(noticeUpdateRequest, writerId)
        return noticeRepository.save(notice)
    }

    fun delete(boardId: Long): Boolean {
        return try {
            findById(boardId).markAsDeleted()
            true
        } catch (e: NotFoundException) {
            log.error(DELETE_FAIL(ENTITY_NAME), boardId, e)
            false
        }
    }

    fun delete(boardId: Long,
               writerId: Long): Boolean {
        return try {
            findById(boardId).checkAndDelete(writerId)
        } catch (e: NotFoundException) {
            log.error(DELETE_FAIL(ENTITY_NAME), boardId, e)
            false
        }
    }

}
