package com.hsmchurch.app.reply.ui.controllers;

import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.reply.domain.Reply;
import com.hsmchurch.app.reply.ui.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.ui.request.ReplyUpdateRequest;
import com.hsmchurch.app.reply.ui.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.hsmchurch.app.security.AuthenticationHelper.currentUserId;
import static com.hsmchurch.app.security.AuthenticationHelper.currentUserIsAdmin;

@RestController
@RequestMapping(path = "/api/v1/me/replies")
@RequiredArgsConstructor
public class MeReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Void> apply(@RequestBody final ReplyApplyRequest replyApplyRequest,
                                      UriComponentsBuilder b) {
        ReplyResponse response = replyService.apply(replyApplyRequest, currentUserId()).toResponse();
        UriComponents uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(response.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping
    public ResponseEntity<Page<ReplyResponse>> getAll(final Pageable pageable) {
        final Page<ReplyResponse> result = replyService.findAllByWriter(currentUserId(), pageable).map(Reply::toResponse);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,
                                       @RequestBody final ReplyUpdateRequest replyUpdateRequest) {
        replyService.update(replyUpdateRequest, id, currentUserId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByReplier(@PathVariable final Long id) {
        if (currentUserIsAdmin()) {
            if (replyService.forceDeleteReply(id)) return ResponseEntity.noContent().build();
            return ResponseEntity.badRequest().build();
        }
        if (replyService.checkDeleteReply(id, currentUserId())) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(@RequestParam Long videoId) {
        if (replyService.deleteReplies(videoId, currentUserId())) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
