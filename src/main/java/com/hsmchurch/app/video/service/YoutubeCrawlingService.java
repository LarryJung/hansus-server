package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.api.dto.response.VideoResponseDto;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.entity.repository.VideoRepository;
import com.hsmchurch.app.video.support.YoutubeCrawler;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class YoutubeCrawlingService {

    private final VideoRepository videoRepository;
    private final YoutubeCrawler youtubeCrawler;

    public Optional<List<VideoResponseDto>> updateVideos() {

        try {
            final List<Video> result = youtubeCrawler.collectInfos(null, new ArrayList<>()).stream()
                    .map(Video::from).collect(toList());

            return Optional.of(videoRepository.saveAll(result).stream()
                    .map(Video::toResponseDto)
                    .collect(toList())
            );

        } catch (JSONException e) {
            return Optional.empty();
        }
    }
}
