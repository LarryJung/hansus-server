package com.hsmchurch.app.video.application

import com.hsmchurch.app.video.domain.Video
import com.hsmchurch.app.video.domain.VideoRepository
import com.hsmchurch.app.video.support.DescriptionParser
import com.hsmchurch.app.video.support.YoutubeCrawler
import com.hsmchurch.app.video.ui.response.VideoListResponse
import lombok.RequiredArgsConstructor
import org.json.JSONException
import org.springframework.stereotype.Service

import java.util.ArrayList

import java.util.stream.Collectors.toList

@Service
class YoutubeCrawlingService constructor(
        private val videoRepository: VideoRepository,
        private val youtubeCrawler: YoutubeCrawler,
        private val descriptionParser: DescriptionParser
) {

    fun updateVideos(): List<VideoListResponse>? {
        return try {
            val result = youtubeCrawler
                    .collectInfos(null, mutableListOf())
                    .map { info -> Video.from(info, descriptionParser) }
            videoRepository.saveAll(result).map { it.toResponseDto() }
        } catch (e: JSONException) {
            null
        }

    }
}
