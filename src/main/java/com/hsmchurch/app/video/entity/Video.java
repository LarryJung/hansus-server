package com.hsmchurch.app.video.entity;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.support.AboutTimeHelper;
import com.hsmchurch.app.video.api.dto.request.YoutubeVideoInfo;
import com.hsmchurch.app.video.api.dto.response.VideoResponse;
import com.hsmchurch.app.video.entity.value.BibleContent;
import com.hsmchurch.app.video.entity.value.Thumbnail;
import com.hsmchurch.app.video.entity.value.type.VideoType;
import com.hsmchurch.app.video.support.DescriptionParseResult;
import com.hsmchurch.app.video.support.DescriptionParser;
import lombok.*;

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
public class Video extends BaseEntity {

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

    public static Video from(final YoutubeVideoInfo youtubeVideoInfo, final DescriptionParser descriptionParser) {
        final DescriptionParseResult parsedResult = youtubeVideoInfo.parseDescription(descriptionParser);
        return Video.builder()
                .title(youtubeVideoInfo.getTitle())
                .filmedAt(parsedResult.getFilmedAt())
                .preacher(parsedResult.getPreacher())
                .bibleContents(parsedResult.getBibleContents())
                .youtubeId(youtubeVideoInfo.getId())
                .youtubePublishedAt(AboutTimeHelper.parse(youtubeVideoInfo.getPublishedAt()))
                .thumbnail(
                        Thumbnail.of(
                                youtubeVideoInfo.getThumbNail().getThumbnailUrl(),
                                youtubeVideoInfo.getThumbNail().getThumbnailWidth(),
                                youtubeVideoInfo.getThumbNail().getThumbnailHeight()))
                .videoType(VideoType.PREACH)
                .build();
    }

    public VideoResponse toResponseDto() {
        return VideoResponse.builder()
                .id(this.id)
                .filmedAt(this.filmedAt)
                .videoType(this.videoType)
                .youtubeId(this.youtubeId)
                .youtubePublishedAt(this.youtubePublishedAt)
                .title(this.title)
                .preacher(this.preacher)
                .bibleContents(this.bibleContents)
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
}
