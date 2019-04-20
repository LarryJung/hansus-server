package com.hsmchurch.app.noticeboard.ui.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class NoticeUploadRequest(val title: String,
                               val content: String)
