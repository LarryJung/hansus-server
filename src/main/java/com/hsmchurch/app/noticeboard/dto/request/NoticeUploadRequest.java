package com.hsmchurch.app.noticeboard.dto.request;

import lombok.Value;

@Value
public class NoticeUploadRequest {

    private final Long writerId;

    private final String writerName;

    private final String title;

    private final String content;

    public NoticeUploadRequest(Long writerId, String writerName, String title, String content) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
    }
}
