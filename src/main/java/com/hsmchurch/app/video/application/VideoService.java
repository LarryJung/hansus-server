package com.hsmchurch.app.video.application;

import com.hsmchurch.app.video.domain.QVideo;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.domain.VideoRepository;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import com.hsmchurch.app.video.ui.response.VideoDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoSupportService videoSupportService;
    private final static QVideo video = QVideo.video;

    public Page<Video> listOfVideo(final Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public Page<Video> likeList(final Long accountId,
                                final Pageable pageable) {
        final List<Long> videoIds = videoSupportService.likeList(accountId);

        return videoRepository.findAll(video.id.in(videoIds), pageable);
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
