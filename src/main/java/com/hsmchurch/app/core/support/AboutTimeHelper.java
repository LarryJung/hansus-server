package com.hsmchurch.app.core.support;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class AboutTimeHelper {

    public static LocalDateTime parse(final String dateInString) {
        Instant instant = Instant.parse(dateInString);
        return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }

}
