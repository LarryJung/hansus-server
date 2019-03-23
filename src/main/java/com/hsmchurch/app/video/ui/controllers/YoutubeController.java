package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.ui.response.VideoResponse;
import com.hsmchurch.app.video.application.YoutubeCrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubeCrawlingService youtubeCrawlingService;

    @PostMapping("/batch/youtube")
    public List<VideoResponse> parsing() {
        return youtubeCrawlingService.updateVideos()
                .orElseThrow(() -> new RuntimeException("영상 업데이트에 실패했습니다."));
    }

}
