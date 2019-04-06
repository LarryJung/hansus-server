package com.hsmchurch.app.noticeboard.ui.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NoticeDeleteRequest {

    private Long boardId;
    private Long writerId;

    public NoticeDeleteRequest(final Long boardId,
                               final Long writerId) {
        this.boardId = boardId;
        this.writerId = writerId;
    }
}
