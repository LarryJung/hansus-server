package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.application.VideoService;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.ui.response.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponse> findOne(@PathVariable Long id) {
        final VideoResponse videoResponse = videoService.findById(id).toResponseDto();

        return ResponseEntity.ok(videoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<VideoResponse>> findAll(Pageable pageable) {
        final Page<VideoResponse> result = videoService.listOfVideo(pageable).map(Video::toResponseDto);

        return ResponseEntity.ok(result);
    }

}
