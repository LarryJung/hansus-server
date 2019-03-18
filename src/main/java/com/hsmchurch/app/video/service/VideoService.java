package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.entity.LikeTag;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.entity.repository.VideoRepository;
import com.hsmchurch.app.video.api.dto.response.VideoResponseDto;
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
    private final LikeTagService likeTagService;

    //TODO 전체 목록 조회, 리액션 등록, 리액션 삭제, 리액션 수정, 좋아요 별 영상목록 조회

    public Page<VideoResponseDto> listOfVideo(final Pageable pageable) {

        return videoRepository.findAll(pageable).map(Video::toResponseDto);
    }

    // 정렬 조건이 필요함
    public List<VideoResponseDto> likeList(final Long accountId) {

        final List<Long> videoIds = likeTagService.findAllByAccountId(accountId).stream()
                .map(LikeTag::getVideoId).collect(toList());

        return videoRepository.findAllById(videoIds).stream()
                .map(Video::toResponseDto)
                .sorted(Comparator.comparing(VideoResponseDto::getFilmedAt).reversed())
                .collect(toList());
    }

    public Video findById(final Long id) {

        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("영상을 찾을 수 없습니다."));
    }

}
