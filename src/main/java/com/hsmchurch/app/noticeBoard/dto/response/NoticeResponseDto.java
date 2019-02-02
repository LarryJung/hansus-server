package com.hsmchurch.app.noticeBoard.dto.response;

import com.hsmchurch.app.account.api.dto.request.AccountResponseDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoticeResponseDto {

    private final Long id;

    private final String title;

    private final String content; // 단순 글이 아닐 것이므로 향후 객체화 가능

    private final AccountResponseDto writer;

}