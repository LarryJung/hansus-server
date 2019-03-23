package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.entity.repository.VideoRepository;
import com.hsmchurch.app.video.api.dto.response.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<VideoResponse> listOfVideo(final Pageable pageable) {
        return videoRepository.findAll(pageable).map(Video::toResponseDto);
    }

    public List<VideoResponse> likeList(final Long accountId) {
        final List<Long> videoIds = videoSupportService.likeList(accountId);

        return videoRepository.findAllById(videoIds).stream()
                .map(Video::toResponseDto)
                .sorted(Comparator.comparing(VideoResponse::getFilmedAt).reversed())
                .collect(toList());
    }

    public Video findById(final Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("영상을 찾을 수 없습니다."));
    }

}
