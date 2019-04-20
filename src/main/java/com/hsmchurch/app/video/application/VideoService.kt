package com.hsmchurch.app.video.application

import com.hsmchurch.app.video.domain.Video
import com.hsmchurch.app.video.domain.VideoRepository
import com.hsmchurch.app.video.ui.response.VideoDetailResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class VideoService constructor(
        private val videoRepository: VideoRepository,
        private val videoSupportService: VideoSupportService
) {
    private fun findById(id: Long): Video =
            videoRepository.findByIdOrNull(id) ?: throw RuntimeException("영상을 찾을 수 없습니다.")

    fun listOfVideo(pageable: Pageable): Page<Video> =
        videoRepository.findAll(pageable)

    fun likeList(accountId: Long,
                 pageable: Pageable): PageImpl<Video> {
        val videoIds = videoSupportService.likeList(accountId, pageable)
        val videos = videoRepository.findAllById(videoIds).sortedByDescending { it.filmedAt }

        return PageImpl(videos, pageable, videoIds.totalElements)
    }

    fun findDetailById(id: Long): VideoDetailResponse {
        val video = findById(id)
        val likeUsers = videoSupportService.likeUsers(id)
        val replies = videoSupportService.replies(id)
        return video.toDetailResponseDto(likeUsers, replies)
    }
}
