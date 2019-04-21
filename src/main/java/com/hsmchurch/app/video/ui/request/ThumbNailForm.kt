package com.hsmchurch.app.video.ui.request

import lombok.Builder
import lombok.Value


data class ThumbNailForm(
        val thumbnailUrl: String,
        val thumbnailWidth: Int,
        val thumbnailHeight: Int)
