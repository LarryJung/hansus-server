package com.hsmchurch.app.video.domain

import com.hsmchurch.app.video.ui.response.LikeUser

interface LikeTagRepositoryCustom {

    fun findAllByVideo(video: Long): List<LikeUser>
}
