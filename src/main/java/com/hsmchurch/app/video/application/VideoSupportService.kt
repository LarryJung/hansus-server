package com.hsmchurch.app.video.application

import com.hsmchurch.app.reply.application.ReplyService
import com.hsmchurch.app.video.ui.response.LikeUser
import com.hsmchurch.app.video.ui.response.ReplyForVideo
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class VideoSupportService constructor(
        private val likeTagService: LikeTagService,
        private val replyService: ReplyService
){

    fun likeList(accountId: Long,
                 pageable: Pageable): Page<Long> = likeTagService.findAllByAccountId(accountId, pageable).map { it.videoId }

    fun likeUsers(videoId: Long): List<LikeUser> = likeTagService.findAllByVideo(videoId)

    fun replies(id: Long): List<ReplyForVideo> = replyService.findAllByVideo(id)
}
