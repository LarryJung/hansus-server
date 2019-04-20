package com.hsmchurch.app.feed

import com.hsmchurch.app.noticeboard.application.NoticeService
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse
import com.hsmchurch.app.video.application.VideoService
import com.hsmchurch.app.video.ui.response.VideoListResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FeedService constructor(
        private val videoService: VideoService,
        private val noticeService: NoticeService
) {

    fun getRecentFeed(pageable: Pageable): Feed {
        val noticeResponsePage: Page<NoticeResponse?> = noticeService.findListAll(pageable)
        val videoListResponsePage: Page<VideoListResponse?> = videoService.listOfVideo(pageable).map { it.toResponseDto() }

        val noticeFeeds = noticeResponsePage.content.map { it?.toFeed() }
        val videoFeeds = videoListResponsePage.content.map { it?.toFeed() }
        val totalFeeds = (noticeFeeds + videoFeeds).sortedWith(compareByDescending { a -> a?.createdAt })

        return Feed(totalFeeds)
    }
}
