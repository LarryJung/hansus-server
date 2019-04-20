package com.hsmchurch.app.video.ui.request

data class ReactionApplyRequest(val videoId: Long,
                                val accountId: Long,
                                val reactionId: Long,
                                val reactionName: String,
                                val reactionImageUrl: String)
