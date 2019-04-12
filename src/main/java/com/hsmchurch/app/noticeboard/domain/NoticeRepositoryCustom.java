package com.hsmchurch.app.noticeboard.domain;

import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<NoticeResponse> findListAll(Pageable pageable);

    NoticeResponse findOneWithWriterName(Long noticeId);
}
