package com.hsmchurch.app.reply;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.reply.dto.response.ReplyResponseDto;
import com.hsmchurch.app.video.entity.Video;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "replies")
@EqualsAndHashCode(callSuper = false)
@Entity
public class Reply extends BaseEntity {

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

    public Reply(Video video, Account writer, String content) {
        this.video = video;
        this.writer = writer;
        this.content = content;
    }

    public Reply(Long id, Video video, Account writer, String content) {
        this.id = id;
        this.video = video;
        this.writer = writer;
        this.content = content;
    }

    public static Reply of(Account writer, Video targetVideo, String content) {
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

    public Reply updateContent(final String content) {
        return new Reply(this.id, this.video, this.writer, content);
    }
}
