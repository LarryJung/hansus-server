package com.hsmchurch.app.noticeboard.domain

import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface NoticeRepositoryCustom {

    fun findListAll(pageable: Pageable): Page<NoticeResponse?>

    fun findOneWithWriterName(noticeId: Long): NoticeResponse?
}
