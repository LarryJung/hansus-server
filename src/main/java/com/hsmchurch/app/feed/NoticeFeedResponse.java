package com.hsmchurch.app.feed;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class NoticeFeedResponse implements FeedResponse {


    private final FeedType feedType;
    private final Long id;
    private final String title;
    private final String content; // 단순 글이 아닐 것이므로 향후 객체화 가능
    private final Long writerId;
    private final String writerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

}
