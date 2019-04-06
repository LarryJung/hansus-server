package com.hsmchurch.app.noticeboard.ui.controllers;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/notices")
@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getOneById(@PathVariable Long id) {
        final NoticeResponse noticeResponse = noticeService.findById(id).toResponse();

        return ResponseEntity.ok(noticeResponse);
    }




}
