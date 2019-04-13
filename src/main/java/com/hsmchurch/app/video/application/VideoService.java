package com.hsmchurch.app.video.application;

import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.domain.VideoRepository;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import com.hsmchurch.app.video.ui.response.VideoDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoSupportService videoSupportService;

    public Page<Video> listOfVideo(final Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public PageImpl<Video> likeList(final Long accountId,
                                    final Pageable pageable) {
        final Page<Long> videoIds = videoSupportService.likeList(accountId, pageable);

        final List<Video> videos = videoRepository.findAllById(videoIds).stream()
                .sorted(Comparator.comparing(Video::getFilmedAt).reversed())
                .collect(toList());

        return new PageImpl<>(videos, pageable, videoIds.getTotalElements());
    }

    public Video findById(final Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("영상을 찾을 수 없습니다."));
    }

    public VideoDetailResponse findDetailById(final Long id) {
        final Video video = findById(id);
        final List<LikeUser> likeUsers = videoSupportService.likeUsers(id);
        final List<ReplyForVideo> replies = videoSupportService.replies(id);
        return video.toDetailResponseDto(likeUsers, replies);
    }
}
