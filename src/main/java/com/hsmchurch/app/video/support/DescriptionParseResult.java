package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.domain.BibleContent;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class DescriptionParseResult {

    private final LocalDate filmedAt;
    private final String preacher;
    private final List<BibleContent> bibleContents;

}
