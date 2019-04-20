package com.hsmchurch.app.video.ui.controllers

import com.hsmchurch.app.video.application.YoutubeCrawlingService
import com.hsmchurch.app.video.ui.response.VideoListResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class YoutubeController constructor(
        private val youtubeCrawlingService: YoutubeCrawlingService
) {

    @PostMapping("/batch/youtube")
    fun parsing(): List<VideoListResponse> {
        return youtubeCrawlingService.updateVideos() ?: throw RuntimeException("영상 업데이트에 실패했습니다.")
    }

}
