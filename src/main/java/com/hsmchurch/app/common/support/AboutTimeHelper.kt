package com.hsmchurch.app.common.support

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun String.parseToLocalDate(): LocalDateTime {
    val instant = Instant.parse(this)
    return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.id))
}