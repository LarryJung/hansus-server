package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.entity.value.BibleContent;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class DescriptionParser {

    private LocalDate filmedAt;
    private String preacher;
    private List<BibleContent> bibleContents;

    public static DescriptionParser of(final String description) {
        return null;
    }
}
