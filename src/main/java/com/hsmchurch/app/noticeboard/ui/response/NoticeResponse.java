package com.hsmchurch.app.noticeboard.ui.response;

import com.hsmchurch.app.feed.FeedResponse;
import com.hsmchurch.app.feed.FeedType;
import com.hsmchurch.app.feed.NoticeFeedResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private String writerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedResponse toFeed() {
        return NoticeFeedResponse.builder()
                .feedType(FeedType.NOTICE)
                .id(id)
                .title(title)
                .content(content)
                .writerId(writerId)
                .writerName(writerName)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
