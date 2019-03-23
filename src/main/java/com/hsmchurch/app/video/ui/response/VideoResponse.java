package com.hsmchurch.app.video.ui.response;

import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.VideoType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class VideoResponse {

    private final Long id;

    private final String youtubeId;

    private final VideoType videoType;

    private LocalDateTime youtubePublishedAt;

    private LocalDate filmedAt;

    private String title;

    private String preacher;

    private List<BibleContent> bibleContents;

}
