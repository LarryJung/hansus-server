package com.hsmchurch.app.video.ui.controllers;

import com.hsmchurch.app.video.application.LikeTagService;
import com.hsmchurch.app.video.application.VideoService;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.ui.request.LikeTagRequest;
import com.hsmchurch.app.video.ui.response.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hsmchurch.app.security.AuthenticationHelper.currentUserId;

@RestController
@RequestMapping(path = "/api/v1/me/videos")
@RequiredArgsConstructor
public class MeVideoController {

    private final VideoService videoService;
    private final LikeTagService likeTagService;

    @GetMapping("/like")
    ResponseEntity<Page<VideoListResponse>> findAllLikedList(Pageable pageable) {
        final Page<VideoListResponse> result = videoService.likeList(currentUserId(), pageable)
                .map(Video::toResponseDto);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/like")
    ResponseEntity<Void> likeVideo(@PathVariable Long id) {
        final LikeTagRequest likeTagRequest = new LikeTagRequest(id, currentUserId());
        likeTagService.applyLikeTag(likeTagRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/like")
    ResponseEntity<Void> cancelLikeVideo(@PathVariable Long id) {
        final LikeTagRequest likeTagRequest = new LikeTagRequest(id, currentUserId());
        if (likeTagService.cancelLikeTag(likeTagRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
