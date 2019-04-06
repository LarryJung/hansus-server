package com.hsmchurch.app.noticeboard.ui.controllers;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.domain.Notice;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponseForList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.hsmchurch.app.security.AuthenticationHelper.currentUserId;
import static com.hsmchurch.app.security.AuthenticationHelper.currentUserIsAdmin;

@RequestMapping(path = "/api/v1/me/notices")
@RestController
@RequiredArgsConstructor
public class MeNoticeBoardController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody NoticeUploadRequest noticeUploadRequest,
                                         UriComponentsBuilder b) {
        final NoticeResponse noticeResponse = noticeService.upload(noticeUploadRequest, currentUserId()).toResponse();
        UriComponents uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(noticeResponse.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping
    public ResponseEntity<Page<NoticeResponseForList>> getList(Pageable pageable) {
        final Page<NoticeResponseForList> noticeResponses = noticeService.findAll(pageable).map(Notice::toResponseForList);

        return ResponseEntity.ok(noticeResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody NoticeUpdateRequest noticeUpdateRequest) {
        noticeService.updateNotice(noticeUpdateRequest, id, currentUserId());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (currentUserIsAdmin()) {
            if (noticeService.delete(id)) return ResponseEntity.noContent().build();
            return ResponseEntity.badRequest().build();
        }
        if (noticeService.delete(id, currentUserId())) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
