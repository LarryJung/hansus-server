package com.hsmchurch.app.noticeBoard.dto.request;

import lombok.Value;

@Value
public class UpdateForm {

    final private Long boardId;

    final private String title;

    final private String content;

    final private Long writerId;

    public UpdateForm(final Long boardId, final String title, final String content, final Long writerId) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }
}
