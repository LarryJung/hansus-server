package com.hsmchurch.app.reply.ui.controllers;

import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.reply.domain.Reply;
import com.hsmchurch.app.reply.ui.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.ui.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.ui.request.ReplyUpdateRequest;
import com.hsmchurch.app.reply.ui.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.ui.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/replies/{id}")
    public ResponseEntity<ReplyResponse> getOneById(@PathVariable final Long id) {
        final ReplyResponse replyResponse = replyService.findById(id).toResponse();
        return ResponseEntity.ok(replyResponse);
    }

    @PostMapping("/replies/{id}")
    public ResponseEntity<Void> update(@RequestBody final ReplyApplyRequest replyApplyRequest) {
        replyService.apply(replyApplyRequest).toResponse();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/repliers/{replier_id}/replies")
    public ResponseEntity<Page<ReplyResponse>> getAll(@PathVariable final Long replierId,
                                                      final Pageable pageable) {
        final Page<ReplyResponse> result = replyService.findAllByWriter(replierId, pageable).map(Reply::toResponse);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/replies/{id}")
    public ResponseEntity<Void> update(@RequestBody final ReplyUpdateRequest replyUpdateRequest) {
        replyService.update(replyUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/repliers/{replier_id}/replies/{id}")
    public ResponseEntity<Void> deleteByReplier(@RequestBody final ReplyDeleteRequest replyDeleteRequest) {
        if (replyService.deleteReply(replyDeleteRequest)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/videos/{video_id}/replies")
    public ResponseEntity<Void> deleteAll(@RequestBody final TargetVideoRepliesDeleteRequest targetVideoRepliesDeleteRequest) {
        if (replyService.deleteReplies(targetVideoRepliesDeleteRequest)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
