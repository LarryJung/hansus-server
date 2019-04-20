package com.hsmchurch.app.video.ui.controllers

import com.hsmchurch.app.security.AuthenticationHelper.currentUserId
import com.hsmchurch.app.video.application.LikeTagService
import com.hsmchurch.app.video.application.VideoService
import com.hsmchurch.app.video.ui.request.LikeTagRequest
import com.hsmchurch.app.video.ui.response.VideoListResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/me/videos"])
class MeVideoController constructor(
        private val videoService: VideoService,
        private val likeTagService: LikeTagService
) {

    @GetMapping("/like")
    fun findAllLikedList(pageable: Pageable): ResponseEntity<Page<VideoListResponse>> {
        val result = videoService.likeList(currentUserId(), pageable).map { it.toResponseDto() }
        return ResponseEntity.ok(result)
    }

    @PostMapping("/{id}/like")
    fun likeVideo(@PathVariable id: Long): ResponseEntity<Void> {
        likeTagService.applyLikeTag(LikeTagRequest(id, currentUserId()))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}/like")
    fun cancelLikeVideo(@PathVariable id: Long): ResponseEntity<Void> {
        return if (likeTagService.cancelLikeTag(LikeTagRequest(id, currentUserId()))) { ResponseEntity.ok().build() } else ResponseEntity.badRequest().build()
    }

}
