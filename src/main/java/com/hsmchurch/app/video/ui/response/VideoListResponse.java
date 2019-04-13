package com.hsmchurch.app.video.ui.response;

import com.hsmchurch.app.feed.FeedType;
import com.hsmchurch.app.feed.VideoFeedResponse;
import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.Thumbnail;
import com.hsmchurch.app.video.domain.VideoType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class VideoListResponse {

    private final Long id;
    private final String youtubeId;
    private final VideoType videoType;
    private final LocalDateTime youtubePublishedAt;
    private final LocalDate filmedAt;
    private final String title;
    private final String preacher;
    private final List<BibleContent> bibleContents;
    private final Thumbnail thumbnail;

    public VideoFeedResponse toFeed() {
        return VideoFeedResponse.builder()
                .feedType(FeedType.VIDEO)
                .youtubeId(youtubeId)
                .videoType(videoType)
                .youtubePublishedAt(youtubePublishedAt)
                .filmedAt(filmedAt)
                .title(title)
                .preacher(preacher)
                .bibleContents(bibleContents)
                .thumbnail(thumbnail)
                .build();
    }
}
