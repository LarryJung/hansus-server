package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.entity.value.BibleContent;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class DescriptionParseResult {

    private final LocalDate filmedAt;
    private final String preacher;
    private final List<BibleContent> bibleContents;

    public static DescriptionParseResult of(final String description) {
        return null;
    }
}
