package com.hsmchurch.app.noticeboard.ui.controllers

import com.hsmchurch.app.noticeboard.application.NoticeService
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping(path = ["/api/v1/notices"])
@RestController
class NoticeBoardController constructor(
        private val noticeService: NoticeService
) {

    @GetMapping("/{id}")
    fun getOneById(@PathVariable id: Long): ResponseEntity<NoticeResponse> =
            ResponseEntity.ok(noticeService.findById2(id))

    // 목록 조회 인증을 받은 누구나 호출 가능
    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity<Page<NoticeResponse?>> =
        ResponseEntity.ok(noticeService.findListAll(pageable))

}
