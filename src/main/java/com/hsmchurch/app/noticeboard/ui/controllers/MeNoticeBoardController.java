package com.hsmchurch.app.noticeboard.ui.controllers;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.domain.Notice;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> register(@RequestBody final NoticeUploadRequest noticeUploadRequest,
                                         UriComponentsBuilder b) {
        final Notice savedNotice = noticeService.upload(noticeUploadRequest, currentUserId());
        UriComponents uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(savedNotice.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,
                                       @RequestBody final NoticeUpdateRequest noticeUpdateRequest) {
        noticeService.updateNotice(noticeUpdateRequest, id, currentUserId());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        if (currentUserIsAdmin()) {
            if (noticeService.delete(id)) return ResponseEntity.noContent().build();
            return ResponseEntity.badRequest().build();
        }
        if (noticeService.delete(id, currentUserId())) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
