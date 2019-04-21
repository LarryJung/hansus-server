package com.hsmchurch.app.video.domain

import com.hsmchurch.app.common.BaseEntity
import com.hsmchurch.app.common.Feedable
import com.hsmchurch.app.common.support.*
import com.hsmchurch.app.video.support.DescriptionParser
import com.hsmchurch.app.video.ui.request.YoutubeVideoInfo
import com.hsmchurch.app.video.ui.response.LikeUser
import com.hsmchurch.app.video.ui.response.ReplyForVideo
import com.hsmchurch.app.video.ui.response.VideoDetailResponse
import com.hsmchurch.app.video.ui.response.VideoListResponse
import javax.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

import javax.persistence.GenerationType.IDENTITY

@Table(name = "videos")
@Entity
data class Video(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Long = -1,

        @Column(name = "video_type")
        @Enumerated(EnumType.STRING)
        var videoType: VideoType,

        @Column(name = "filmed_at")
        var filmedAt: LocalDate,

        @Column(name = "title")
        var title: String,

        @Column(name = "preacher")
        var preacher: String?,

        @ElementCollection
        @CollectionTable(name = "bible_contents", joinColumns = [JoinColumn(name = "video_id")])
        var bibleContents: List<BibleContent>?,

        @Column(name = "youtube_id", unique = true)
        var youtubeId: String,

        @Column(name = "youtube_published_at")
        var youtubePublishedAt: LocalDateTime,

        @Embedded
        var thumbnail: Thumbnail
) : BaseEntity() {

    fun toResponseDto(): VideoListResponse {
        return VideoListResponse(
                id = this.id,
                filmedAt = this.filmedAt,
                videoType = this.videoType,
                youtubeId = this.youtubeId,
                youtubePublishedAt = this.youtubePublishedAt,
                title = this.title,
                preacher = this.preacher,
                bibleContents = this.bibleContents,
                thumbnail = this.thumbnail)
    }

    fun toDetailResponseDto(likeUsers: List<LikeUser>,
                            replies: List<ReplyForVideo>): VideoDetailResponse {
        return VideoDetailResponse(
                id = id,
                bibleContents = bibleContents,
                filmedAt = filmedAt,
                preacher = preacher,
                title = title,
                videoType = videoType,
                youtubeId = youtubeId,
                youtubePublishedAt = youtubePublishedAt,
                likeUsers = likeUsers,
                replies = replies
        )
    }

    companion object {
        fun from(youtubeVideoInfo: YoutubeVideoInfo, descriptionParser: DescriptionParser): Video {
            val result = youtubeVideoInfo.parseDescription(descriptionParser)
            return Video(
                    title = youtubeVideoInfo.title,
                    filmedAt = result.filmedAt,
                    preacher = result.preacher,
                    bibleContents = result.bibleContents,
                    youtubeId = youtubeVideoInfo.id,
                    youtubePublishedAt = youtubeVideoInfo.publishedAt.parseToLocalDate(),
                    thumbnail = Thumbnail.of(
                            youtubeVideoInfo.thumbNail.thumbnailUrl,
                            youtubeVideoInfo.thumbNail.thumbnailWidth,
                            youtubeVideoInfo.thumbNail.thumbnailHeight),
                    videoType = VideoType.PREACH
            )
        }
    }
}
