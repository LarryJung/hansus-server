package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.application.VideoService;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.ui.response.VideoDetailResponse;
import com.hsmchurch.app.video.ui.response.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    // 비디오 상세 조회 (실제 시용예정)
    @GetMapping("/{id}")
    public ResponseEntity<VideoDetailResponse> findOneDetails(@PathVariable Long id) {
        final VideoDetailResponse videoDetailResponse = videoService.findDetailById(id);
        return ResponseEntity.ok(videoDetailResponse);
    }

    // 비디오 목록 조회
    @GetMapping
    public ResponseEntity<Page<VideoListResponse>> findAll(Pageable pageable) {
        final Page<VideoListResponse> result = videoService.listOfVideo(pageable).map(Video::toResponseDto);
        System.out.println(result.getContent());
        return ResponseEntity.ok(result);
    }

}
