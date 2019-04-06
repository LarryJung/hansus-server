package com.hsmchurch.app.noticeboard.ui.response;

import com.hsmchurch.app.noticeboard.domain.Writer;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NoticeResponseForList {

    private final Long id;

    private final String title;

    private final Writer writer;

}
