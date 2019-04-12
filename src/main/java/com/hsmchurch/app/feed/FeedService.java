package com.hsmchurch.app.feed;

import com.hsmchurch.app.noticeboard.application.NoticeService;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import com.hsmchurch.app.video.application.VideoService;
import com.hsmchurch.app.video.domain.Video;
import com.hsmchurch.app.video.ui.response.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class FeedService {

    private final VideoService videoService;
    private final NoticeService noticeService;

    public Feed getRecentFeed(Pageable pageable) {
        final Page<NoticeResponse> noticeResponsePage = noticeService.findListAll(pageable);
        final Page<VideoListResponse> videoListResponsePage = videoService.listOfVideo(pageable).map(Video::toResponseDto);

        final List<FeedResponse> noticeFeeds = noticeResponsePage.getContent().stream().map(NoticeResponse::toFeed).collect(toList());
        final List<FeedResponse> videoFeeds = videoListResponsePage.getContent().stream().map(VideoListResponse::toFeed).collect(toList());
        final List<FeedResponse> totalFeeds = Stream.concat(noticeFeeds.stream(),videoFeeds.stream() )
                .sorted(Comparator.comparing(FeedResponse::getCreatedAt).reversed())
                .collect(toList());

        return new Feed(totalFeeds);

    }
}
