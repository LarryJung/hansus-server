package com.hsmchurch.app.noticeBoard.dto.request;

import lombok.Value;

@Value
public class UploadForm {

    private final String title;

    private final String content;

    public UploadForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
