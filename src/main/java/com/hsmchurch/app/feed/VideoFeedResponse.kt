package com.hsmchurch.app.feed

import com.hsmchurch.app.video.domain.BibleContent
import com.hsmchurch.app.video.domain.Thumbnail
import com.hsmchurch.app.video.domain.VideoType
import lombok.Builder
import lombok.Value

import java.time.LocalDate
import java.time.LocalDateTime

data class VideoFeedResponse(val feedType: FeedType,
                             val id: Long,
                             val youtubeId: String,
                             val videoType: VideoType,
                             override val createdAt: LocalDateTime,
                             val filmedAt: LocalDate,
                             val title: String,
                             val preacher: String?,
                             val bibleContents: List<BibleContent>?,
                             val thumbnail: Thumbnail) : FeedResponse
