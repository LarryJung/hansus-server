package com.hsmchurch.app.feed;

import com.hsmchurch.app.video.domain.Thumbnail;
import com.hsmchurch.app.video.domain.VideoType;
import com.hsmchurch.app.video.ui.response.BibleResponse;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class VideoFeedResponse implements FeedResponse {

    private final FeedType feedType;
    private final Long id;
    private final String youtubeId;
    private final VideoType videoType;
    private final LocalDateTime youtubePublishedAt;
    private final LocalDate filmedAt;
    private final String title;
    private final String preacher;
    private final List<BibleResponse> bibleContents;
    private final Thumbnail thumbnail;

    @Override
    public LocalDateTime getCreatedAt() {
        return youtubePublishedAt;
    }
}
