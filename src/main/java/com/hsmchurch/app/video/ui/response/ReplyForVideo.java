package com.hsmchurch.app.video.ui.response;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ReplyForVideo {

    private Long id;
    private String content;
    private Long replierId;
    private String replierName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
