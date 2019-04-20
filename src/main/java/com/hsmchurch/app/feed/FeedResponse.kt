package com.hsmchurch.app.feed

import java.time.LocalDateTime

data class Feed(val feeds: List<FeedResponse?>)

interface FeedResponse {
    val createdAt: LocalDateTime
}


