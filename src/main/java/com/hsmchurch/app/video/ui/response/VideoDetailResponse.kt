package com.hsmchurch.app.video.ui.response

import com.hsmchurch.app.video.domain.BibleContent
import com.hsmchurch.app.video.domain.VideoType
import lombok.Builder
import lombok.Value

import java.time.LocalDate
import java.time.LocalDateTime

data class VideoDetailResponse(val id: Long,
                               val youtubeId: String,
                               val videoType: VideoType,
                               val youtubePublishedAt: LocalDateTime,
                               val filmedAt: LocalDate,
                               val title: String,
                               val preacher: String?,
                               val bibleContents: List<BibleContent>?,
                               val likeUsers: List<LikeUser>,
                               val replies: List<ReplyForVideo>)
