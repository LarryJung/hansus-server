package com.hsmchurch.app.video.api;

import com.hsmchurch.app.video.api.dto.response.VideoResponseDto;
import com.hsmchurch.app.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class YoutubeController {

    private final VideoService videoService;

    @PostMapping("/batch/youtube")
    public List<VideoResponseDto> parsing() {
        return videoService.updateVideos()
                .orElseThrow(() -> new RuntimeException("영상 업데이트에 실패했습니다."));
    }

}
