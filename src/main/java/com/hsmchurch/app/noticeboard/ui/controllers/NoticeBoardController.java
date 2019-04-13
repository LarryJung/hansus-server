package com.hsmchurch.app.noticeboard.ui.controllers;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/api/v1/notices")
@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getOneById(@PathVariable Long id) {
        final NoticeResponse noticeResponse = noticeService.findById2(id);

        return ResponseEntity.ok(noticeResponse);
    }

    // 목록 조회 인증을 받은 누구나 호출 가능
    @GetMapping
    public ResponseEntity<Page<NoticeResponse>> getAll(Pageable pageable) {
        final Page<NoticeResponse> noticeResponse = noticeService.findListAll(pageable);

        return ResponseEntity.ok(noticeResponse);
    }

}
