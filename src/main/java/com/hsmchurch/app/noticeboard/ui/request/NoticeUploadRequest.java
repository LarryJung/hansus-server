package com.hsmchurch.app.noticeboard.ui.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NoticeUploadRequest {

    private Long writerId;

    private String writerName;

    private String title;

    private String content;

    public NoticeUploadRequest(Long writerId, String writerName, String title, String content) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
    }
}
