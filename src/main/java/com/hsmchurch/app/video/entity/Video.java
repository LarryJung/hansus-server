package com.hsmchurch.app.video.entity;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.support.AboutTimeHelper;
import com.hsmchurch.app.video.api.dto.request.YoutubeForm;
import com.hsmchurch.app.video.api.dto.response.VideoResponseDto;
import com.hsmchurch.app.video.entity.value.BibleContent;
import com.hsmchurch.app.video.entity.value.ThumbNail;
import com.hsmchurch.app.video.entity.value.type.VideoType;
import com.hsmchurch.app.video.support.DescriptionParser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "videos")
@Entity
public class Video extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private VideoType videoType;

    @Column
    private LocalDate filmedAt;

    @Column
    private String title;

    @Column
    private String preacher;

    @ElementCollection
    @CollectionTable(
            name = "bible_contents",
            joinColumns = @JoinColumn(name = "video_id")
    )
    private List<BibleContent> bibleContents;

    @Column(unique = true)
    private String youtubeId;

    @Column
    private LocalDateTime youtubePublishedAt;

    @Column
    @Embedded
    private ThumbNail thumbNail;

    public static Video from(final YoutubeForm youtubeForm) {
        final DescriptionParser parsedResult = DescriptionParser.of(youtubeForm.getDescription());
        return Video.builder()
                .title(youtubeForm.getTitle())
                .filmedAt(parsedResult.getFilmedAt())
                .preacher(parsedResult.getPreacher())
                .bibleContents(parsedResult.getBibleContents())
                .youtubeId(youtubeForm.getId())
                .youtubePublishedAt(AboutTimeHelper.parse(youtubeForm.getPublishedAt()))
                .thumbNail(
                        ThumbNail.of(
                                youtubeForm.getThumbNail().getThumbnailUrl(),
                                youtubeForm.getThumbNail().getThumbnailWidth(),
                                youtubeForm.getThumbNail().getThumbnailHeight()
                        )
                )
                .videoType(VideoType.PREACH)
                .build();
    }

    public VideoResponseDto toResponseDto() {
        return VideoResponseDto.builder()
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
}
