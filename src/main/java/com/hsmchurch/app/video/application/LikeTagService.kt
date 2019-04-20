package com.hsmchurch.app.video.application

import com.hsmchurch.app.common.exceptions.NotFoundException
import com.hsmchurch.app.common.support.CrudStringFormat.CANCEL_FAIL
import com.hsmchurch.app.common.support.logger
import com.hsmchurch.app.video.domain.LikeTag
import com.hsmchurch.app.video.domain.LikeTagId
import com.hsmchurch.app.video.domain.LikeTagRepository
import com.hsmchurch.app.video.ui.request.LikeTagRequest
import com.hsmchurch.app.video.ui.response.LikeUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class LikeTagService constructor(
        private val likeTagRepository: LikeTagRepository
) {
    companion object {
        private const val ENTITY_NAME = "좋아요 태그"
        private val log = logger()
    }

    fun applyLikeTag(likeTagRequest: LikeTagRequest) {
        likeTagRepository.save(LikeTag.of(likeTagRequest))
    }

    fun cancelLikeTag(likeTagRequest: LikeTagRequest): Boolean {
        return try {
            findBy(likeTagRequest.videoId, likeTagRequest.accountId).cancel()
            true
        } catch (e: NotFoundException) {
            log.error(CANCEL_FAIL(ENTITY_NAME), likeTagRequest, e)
            false
        }
    }

    fun findAllByAccountId(accountId: Long,
                           pageable: Pageable): Page<LikeTag> {
        return likeTagRepository.findAllByLikeTagId_AccountId(accountId, pageable)
    }

    fun findByVideoId(videoId: Long): List<LikeTag> {
        return likeTagRepository.findAllByLikeTagId_VideoId(videoId)
    }

    fun findAllByVideo(videoId: Long): List<LikeUser> {
        return likeTagRepository.findAllByVideo(videoId)
    }

    private fun findBy(videoId: Long,
                       accountId: Long): LikeTag {
        val id = LikeTagId(videoId, accountId)

        return likeTagRepository.findByLikeTagId(id) ?: throw NotFoundException("좋아요 태그", id)
    }

}
