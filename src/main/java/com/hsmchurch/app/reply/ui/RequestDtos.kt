package com.hsmchurch.app.reply.ui

data class ReplyApplyRequest(val videoId: Long,
                             val content: String)

data class ReplyUpdateRequest(val content: String)