package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.common.BaseEntity;
import com.hsmchurch.app.common.Feedable;
import com.hsmchurch.app.common.support.AboutTimeHelperKt;
import com.hsmchurch.app.video.support.DescriptionParseResult;
import com.hsmchurch.app.video.support.DescriptionParser;
import com.hsmchurch.app.video.ui.request.YoutubeVideoInfo;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import com.hsmchurch.app.video.ui.response.VideoDetailResponse;
import com.hsmchurch.app.video.ui.response.VideoListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "videos")
@Entity
public class Video extends BaseEntity implements Feedable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "video_type")
    @Enumerated(EnumType.STRING)
    private VideoType videoType;

    @Column(name = "filmed_at")
    private LocalDate filmedAt;

    @Column(name = "title")
    private String title;

    @Column(name = "preacher")
    private String preacher;

    @ElementCollection
    @CollectionTable(name = "bible_contents", joinColumns = @JoinColumn(name = "video_id"))
    private List<BibleContent> bibleContents;

    @Column(name = "youtube_id", unique = true)
    private String youtubeId;

    @Column(name = "youtube_published_at")
    private LocalDateTime youtubePublishedAt;

    @Embedded
    private Thumbnail thumbnail;

    @Nonnull
    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    public static Video from(final YoutubeVideoInfo youtubeVideoInfo, final DescriptionParser descriptionParser) {
        final DescriptionParseResult parsedResult = youtubeVideoInfo.parseDescription(descriptionParser);
        return Video.builder()
                .title(youtubeVideoInfo.getTitle())
                .filmedAt(parsedResult.getFilmedAt())
                .preacher(parsedResult.getPreacher())
                .bibleContents(parsedResult.getBibleContents())
                .youtubeId(youtubeVideoInfo.getId())
                .youtubePublishedAt(AboutTimeHelperKt.parseToLocalDate(youtubeVideoInfo.getPublishedAt()))
                .thumbnail(
                        Thumbnail.of(
                                youtubeVideoInfo.getThumbNail().getThumbnailUrl(),
                                youtubeVideoInfo.getThumbNail().getThumbnailWidth(),
                                youtubeVideoInfo.getThumbNail().getThumbnailHeight()))
                .videoType(VideoType.PREACH)
                .build();
    }

    public VideoListResponse toResponseDto() {
        return VideoListResponse.builder()
                .id(this.id)
                .filmedAt(this.filmedAt)
                .videoType(this.videoType)
                .youtubeId(this.youtubeId)
                .youtubePublishedAt(this.youtubePublishedAt)
                .title(this.title)
                .preacher(this.preacher)
                .bibleContents(this.bibleContents)
                .thumbnail(this.thumbnail)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id) &&
                videoType == video.videoType &&
                Objects.equals(filmedAt, video.filmedAt) &&
                Objects.equals(title, video.title) &&
                Objects.equals(preacher, video.preacher) &&
                Objects.equals(bibleContents, video.bibleContents) &&
                Objects.equals(youtubeId, video.youtubeId) &&
                Objects.equals(youtubePublishedAt, video.youtubePublishedAt) &&
                Objects.equals(thumbnail, video.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, videoType, filmedAt, title, preacher, bibleContents, youtubeId, youtubePublishedAt, thumbnail);
    }

    public VideoDetailResponse toDetailResponseDto(final List<LikeUser> likeUsers,
                                                   final List<ReplyForVideo> replies) {
        return VideoDetailResponse.builder()
                .id(id)
                .bibleContents(bibleContents)
                .filmedAt(filmedAt)
                .preacher(preacher)
                .title(title)
                .videoType(videoType)
                .youtubeId(youtubeId)
                .youtubePublishedAt(youtubePublishedAt)
                .likeUsers(likeUsers)
                .replies(replies)
                .build();
    }
}
