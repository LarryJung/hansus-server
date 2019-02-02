package com.hsmchurch.app.noticeBoard.form;

import lombok.Value;

@Value
public class UploadForm {

    final private String title;

    final private String content;

    public UploadForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
