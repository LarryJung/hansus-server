package com.hsmchurch.app.video.ui.controllers

import com.hsmchurch.app.video.application.VideoService
import com.hsmchurch.app.video.ui.response.VideoDetailResponse
import com.hsmchurch.app.video.ui.response.VideoListResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/videos"])
class VideoController constructor(
        private val videoService: VideoService
) {

    @GetMapping("/{id}")
    fun findOneDetails(@PathVariable id: Long): ResponseEntity<VideoDetailResponse> =
            ResponseEntity.ok(videoService.findDetailById(id))

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<VideoListResponse>> =
            ResponseEntity.ok(videoService.listOfVideo(pageable).map { it.toResponseDto() })

}
