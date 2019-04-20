package com.hsmchurch.app.feed

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping(path = ["/api/v1/feeds"])
@RestController
class FeedController constructor(private val feedService: FeedService) {

    @GetMapping
    fun getFeed(pageable: Pageable): ResponseEntity<Feed> {
        IntArray(5) { i -> i*i }
        val feed = feedService.getRecentFeed(pageable)
        return ResponseEntity.ok(feed)
    }

}
