package com.hsmchurch.app.video.ui.controllers

import com.hsmchurch.app.video.application.LikeTagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/likes"])
class LikeTagController constructor(
        private val likeTagService: LikeTagService
) {

    @GetMapping
    fun getAllByVideo(@RequestParam videoId: Long): ResponseEntity<List<Long>> =
            ResponseEntity.ok(likeTagService.findByVideoId(videoId).map { it.writerId })

}