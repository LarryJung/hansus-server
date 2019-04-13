package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.application.LikeTagService;
import com.hsmchurch.app.video.domain.LikeTag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api/v1/likes")
@RequiredArgsConstructor
public class LikeTagController {

    private final LikeTagService likeTagService;

    @GetMapping
    public ResponseEntity<List<Long>> getAllByVideo(@RequestParam Long videoId) {
        return ResponseEntity.ok(likeTagService.findByVideoId(videoId).stream().map(LikeTag::getWriterId).collect(toList()));
    }

}
