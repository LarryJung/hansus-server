package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.application.LikeTagService;
import com.hsmchurch.app.video.application.VideoService;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.ui.request.LikeTagRequest;
import com.hsmchurch.app.video.ui.response.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final LikeTagService likeTagService;

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoResponse> findOne(@PathVariable Long id) {
        final VideoResponse videoResponse = videoService.findById(id).toResponseDto();
        return ResponseEntity.ok(videoResponse);
    }

    @GetMapping("/videos")
    public ResponseEntity<Page<VideoResponse>> findAll(Pageable pageable) {
        final Page<VideoResponse> result = videoService.listOfVideo(pageable).map(Video::toResponseDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{user_id}/videos")
    ResponseEntity<Page<VideoResponse>> findAllLikedList(@PathVariable Long userId, Pageable pageable) {
        final Page<VideoResponse> result = videoService.likeList(userId, pageable)
                .map(Video::toResponseDto);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/videos/{video_id}/like")
    ResponseEntity<Void> likeVideo(@PathVariable Long videoId) {
        final Long loginUserId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LikeTagRequest likeTagRequest = new LikeTagRequest(videoId, loginUserId);
        likeTagService.applyLikeTag(likeTagRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/videos/{video_id}/like")
    ResponseEntity<Void> cancelLikeVideo(@PathVariable Long videoId) {
        final Long loginUserId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LikeTagRequest likeTagRequest = new LikeTagRequest(videoId, loginUserId);
        if (likeTagService.cancelLikeTag(likeTagRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
