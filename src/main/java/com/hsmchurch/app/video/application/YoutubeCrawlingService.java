package com.hsmchurch.app.video.application;

import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.domain.VideoRepository;
import com.hsmchurch.app.video.support.DescriptionParser;
import com.hsmchurch.app.video.support.YoutubeCrawler;
import com.hsmchurch.app.video.ui.response.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class YoutubeCrawlingService {

    private final VideoRepository videoRepository;
    private final YoutubeCrawler youtubeCrawler;
    private final DescriptionParser descriptionParser;

    public List<VideoListResponse> updateVideos() {
        try {
            final List<Video> result = youtubeCrawler.collectInfos(null, new ArrayList<>()).stream()
                    .map(info -> Video.from(info, descriptionParser)).collect(toList());

            return videoRepository.saveAll(result).stream()
                    .map(Video::toResponseDto).collect(toList());
        } catch (JSONException e) {
            return null;
        }
    }
}
