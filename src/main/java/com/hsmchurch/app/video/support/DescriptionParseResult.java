package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.VideoType;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Value
public class DescriptionParseResult {

    private final LocalDate filmedAt;
    private final Optional<String> preacher;
    private final Optional<List<BibleContent>> bibleContents;
    private final VideoType videoType;
}
