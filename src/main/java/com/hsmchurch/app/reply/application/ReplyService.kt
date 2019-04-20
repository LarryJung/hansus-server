package com.hsmchurch.app.reply.application

import com.hsmchurch.app.common.exceptions.NotFoundException
import com.hsmchurch.app.common.support.CrudStringFormat.DELETE_FAIL
import com.hsmchurch.app.common.support.CrudStringFormat.DELETE_SUCCESS
import com.hsmchurch.app.common.support.CrudStringFormat.REGISTER_SUCCESS
import com.hsmchurch.app.common.support.CrudStringFormat.UPDATE_SUCCESS
import com.hsmchurch.app.common.support.logger
import com.hsmchurch.app.reply.domain.Reply
import com.hsmchurch.app.reply.domain.ReplyRepository
import com.hsmchurch.app.reply.ui.ReplyApplyRequest
import com.hsmchurch.app.reply.ui.ReplyUpdateRequest
import com.hsmchurch.app.video.ui.response.ReplyForVideo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplyService constructor(
        private val replyRepository: ReplyRepository
) {
    companion object {
        private const val ENTITY_NAME = "댓글"
        val log = logger()
    }

    fun apply(replyApplyRequest: ReplyApplyRequest,
              replierId: Long): Reply {
        val reply = replyRepository.save(Reply.of(replyApplyRequest, replierId))
        log.info(REGISTER_SUCCESS(ENTITY_NAME), replyApplyRequest)
        return reply
    }

    fun update(replyUpdateRequest: ReplyUpdateRequest,
               replyId: Long,
               replierId: Long): Reply {
        val reply = replyRepository.save(
                findById(replyId).updateContent(replyUpdateRequest.content, replierId)
        )
        log.info(UPDATE_SUCCESS(ENTITY_NAME), replyUpdateRequest)
        return reply
    }

    fun findById(id: Long): Reply {
        return replyRepository.findByIdOrNull(id) ?: throw NotFoundException("댓글", id)
    }

    fun findAllByWriter(replierId: Long,
                        pageable: Pageable): Page<Reply> {
        return replyRepository.findAllByReplier_Id(replierId, pageable)
    }

    fun forceDeleteReply(replyId: Long): Boolean {
        return try {
            findById(replyId).markAsDeleted()
            log.info(DELETE_SUCCESS(ENTITY_NAME), replyId)
            true
        } catch (e: NotFoundException) {
            log.error(DELETE_FAIL(ENTITY_NAME), replyId, e)
            false
        }
    }

    fun checkDeleteReply(replyId: Long,
                         replierId: Long): Boolean {
        return try {
            findById(replyId).checkAndDelete(replierId)
        } catch (e: NotFoundException) {
            false
        }
    }

    fun deleteReplies(videoId: Long,
                      replierId: Long): Boolean {
        val replies = replyRepository.findAllByVideoIdAndReplier_Id(videoId, replierId)
        if (replies.isEmpty()) {
            return false
        }
        replies.forEach{ it.markAsDeleted() }
        return true
    }

    fun findAllByVideo(videoId: Long): List<ReplyForVideo> {
        return replyRepository.videoReplies(videoId)
    }

}
