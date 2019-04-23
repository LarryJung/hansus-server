package com.hsmchurch.app.reply.ui.controllers;

import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.reply.domain.Reply;
import com.hsmchurch.app.reply.ui.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api/v1/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> getOneById(@PathVariable final Long id) {
        final ReplyResponse replyResponse = replyService.findById(id).toResponse();

        return ResponseEntity.ok(replyResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReplyResponse>> getAllOfVideo(@RequestParam final Long videoId) {
        final List<ReplyResponse> replyResponse = replyService.findAllByVideo(videoId).stream().map(Reply::toResponse).collect(toList());

        return ResponseEntity.ok(replyResponse);
    }

}
