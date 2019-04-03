package com.hsmchurch.app.noticeboard.ui.controllers;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.domain.Notice;
import com.hsmchurch.app.noticeboard.ui.request.NoticeDeleteRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping(path = "/api/v1/notices")
@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeService noticeService;

/*
* 게시판 등록
* 게시판 수정
* 게시판 상세 조회
* 게시판 목록 조회 (페이징, 필터)
* */

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getOneById(@PathVariable Long id) {
        final NoticeResponse noticeResponse = noticeService.findById(id).toResponse();

        return ResponseEntity.ok(noticeResponse);
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody  NoticeUploadRequest noticeUploadRequest,
                                         UriComponentsBuilder b){
        final NoticeResponse noticeResponse = noticeService.upload(noticeUploadRequest).toResponse();
        UriComponents uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(noticeResponse.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody NoticeUpdateRequest noticeUpdateRequest) {
        noticeService.updateNotice(noticeUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody NoticeDeleteRequest noticeDeleteRequest) {
        if (noticeService.delete(noticeDeleteRequest)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Page<NoticeResponse>> getList(Pageable pageable) {
        final Page<NoticeResponse> noticeResponses = noticeService.findAll(pageable).map(Notice::toResponse);

        return ResponseEntity.ok(noticeResponses);
    }

}
