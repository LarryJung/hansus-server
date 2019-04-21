package com.hsmchurch.app.video.ui.request

import com.hsmchurch.app.video.support.DescriptionParseResult
import com.hsmchurch.app.video.support.DescriptionParser
import lombok.Builder
import lombok.Value

data class YoutubeVideoInfo(val id: String,
                            val publishedAt: String,
                            val title: String,
                            val description: String?,
                            val thumbNail: ThumbNailForm) {

    fun parseDescription(parser: DescriptionParser): DescriptionParseResult {
        return parser.parse(description!!)
    }

}
