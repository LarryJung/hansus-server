package com.hsmchurch.app.common

import java.time.LocalDateTime

interface Feedable {

    fun getCreatedAt(): LocalDateTime

}