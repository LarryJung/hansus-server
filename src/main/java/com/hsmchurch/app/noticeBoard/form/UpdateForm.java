package com.hsmchurch.app.noticeBoard.form;

import lombok.Value;

@Value
public class UpdateForm {

    final private Long id;

    final private String title;

    final private String content;

    public UpdateForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
