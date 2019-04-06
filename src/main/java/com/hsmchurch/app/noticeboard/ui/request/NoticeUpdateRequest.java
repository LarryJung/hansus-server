package com.hsmchurch.app.noticeboard.ui.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NoticeUpdateRequest {

    private String title;

    private String content;

    public NoticeUpdateRequest(final String title,
                               final String content) {
        this.title = title;
        this.content = content;
    }
}
