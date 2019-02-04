package com.hsmchurch.app.reply.entity;

import com.hsmchurch.app.security.account.entity.Account;
import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.hasOwner;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponseDto;
import com.hsmchurch.app.video.entity.Video;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "replies")
@EqualsAndHashCode(callSuper = false)
@Entity
public class Reply extends BaseEntity implements hasOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Account writer;

    private String content;

    public Reply() {
    }

    public Reply(final Video video, final Account writer, final String content) {
        this.video = video;
        this.writer = writer;
        this.content = content;
    }

    public Reply(final Long id, final Video video, final Account writer, final String content) {
        this.id = id;
        this.video = video;
        this.writer = writer;
        this.content = content;
    }

    public static Reply of(final Account writer, final Video targetVideo, final String content) {
        return new Reply(targetVideo, writer, content);
    }

    public ReplyResponseDto toResponseDto() {
        return ReplyResponseDto.builder()
                .id(this.id)
                .video(this.video)
                .writer(this.writer)
                .content(this.content)
                .build();
    }

    public Reply updateContent(final String content, final Long writerId) {
        if (!isOwner(writerId)) {
            throw new RuntimeException("올바른 작성자가 아닙니다. writer id: " + writerId);
        }
        return new Reply(this.id, this.video, this.writer, content);
    }

    @Override
    public boolean isOwner(Long writerId) {
        return this.writer.isYou(writerId);
    }
}
