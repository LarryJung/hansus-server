package com.hsmchurch.app.noticeboard.ui.response;

import com.hsmchurch.app.feed.FeedResponse;
import com.hsmchurch.app.feed.FeedType;
import com.hsmchurch.app.feed.NoticeFeedResponse;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class NoticeResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Long writerId;
    private final String writerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public FeedResponse toFeed() {
        return NoticeFeedResponse.builder()
                .feedType(FeedType.NOTICE)
                .id(id)
                .title(title)
                .content(content)
                .title(title)
                .writerId(writerId)
                .writerName(writerName)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
