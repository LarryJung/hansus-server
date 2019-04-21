package com.hsmchurch.app.video.ui.response

import com.hsmchurch.app.feed.FeedType
import com.hsmchurch.app.feed.VideoFeedResponse
import com.hsmchurch.app.video.domain.BibleContent
import com.hsmchurch.app.video.domain.Thumbnail
import com.hsmchurch.app.video.domain.VideoType
import lombok.Builder
import lombok.Value

import java.time.LocalDate
import java.time.LocalDateTime

data class VideoListResponse(
        val id: Long,
        val youtubeId: String,
        val videoType: VideoType,
        val youtubePublishedAt: LocalDateTime,
        val filmedAt: LocalDate,
        val title: String,
        val preacher: String?,
        val bibleContents: List<BibleContent>?,
        val thumbnail: Thumbnail
) {

    fun toFeed(): VideoFeedResponse {
        return VideoFeedResponse(
                id = id,
                feedType = FeedType.VIDEO,
                youtubeId = youtubeId,
                videoType = videoType,
                filmedAt = filmedAt,
                title = title,
                preacher = preacher,
                bibleContents = bibleContents,
                thumbnail = thumbnail,
                createdAt = youtubePublishedAt
        )
    }
}
