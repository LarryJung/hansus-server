package com.hsmchurch.app.noticeboard.ui.response;

import com.hsmchurch.app.noticeboard.domain.Writer;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class NoticeListResponse {

    private final Long id;
    private final String title;
    private final Writer writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
