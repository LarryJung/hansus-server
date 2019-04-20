package com.hsmchurch.app.reply.domain

import com.hsmchurch.app.video.ui.response.ReplyForVideo

interface ReplyRepositoryCustom {

    fun videoReplies(videoId: Long): List<ReplyForVideo>

}
