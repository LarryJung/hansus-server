package com.hsmchurch.app.video.ui.response;

import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.VideoType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class VideoDetailResponse {

    private final Long id;
    private final String youtubeId;
    private final VideoType videoType;
    private final LocalDateTime youtubePublishedAt;
    private final LocalDate filmedAt;
    private final String title;
    private final String preacher;
    private final List<BibleContent> bibleContents;
    private final List<LikeUser> likeUsers;
    private final List<ReplyForVideo> replies;

}
