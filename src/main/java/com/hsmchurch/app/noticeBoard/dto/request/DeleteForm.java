package com.hsmchurch.app.noticeBoard.dto.request;

import lombok.Value;

@Value
public class DeleteForm {

    final private Long boardId;

    final private Long writerId;

}
