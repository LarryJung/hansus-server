package com.hsmchurch.app.reply.entity;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.hasOwner;
import com.hsmchurch.app.reply.api.dto.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponse;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "replies")
@Entity
public class Reply extends BaseEntity implements hasOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "video_id")
    private Long videoId;

    @Embedded
    private Replier replier;

    public Reply(final Long videoId,
                 final Replier replier,
                 final String content) {
        this.videoId = videoId;
        this.replier = replier;
        this.content = content;
    }

    public Reply(final Long id,
                 final Long videoId,
                 final Replier replier,
                 final String content) {
        this.id = id;
        this.videoId = videoId;
        this.replier = replier;
        this.content = content;
    }

    public static Reply of(final Long videoId,
                           final Replier replier,
                           final String content) {
        return new Reply(videoId, replier, content);
    }

    public static Reply of(final Long videoId,
                           final Long replierId,
                           final String replierName,
                           final String content) {
        return new Reply(videoId, new Replier(replierId, replierName), content);
    }

    public static Reply of(final ReplyApplyRequest replyApplyRequest) {
        return Reply.of(
                replyApplyRequest.getVideoId(),
                replyApplyRequest.getReplierId(),
                replyApplyRequest.getReplierName(),
                replyApplyRequest.getContent());
    }

    public ReplyResponse toResponse() {
        return ReplyResponse.builder()
                .id(this.id)
                .videoId(videoId)
                .replierId(replier.getId())
                .content(this.content)
                .build();
    }

    public Reply updateContent(final String content,
                               final Long replierId) {
        if (!isOwner(replierId)) {
            throw new RuntimeException("올바른 작성자가 아닙니다. replier id: " + replierId);
        }
        return new Reply(this.id, this.videoId, this.replier, content);
    }

    @Override
    public boolean isOwner(final Long replierId) {
        return this.replier.isYou(replierId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reply reply = (Reply) o;
        return Objects.equals(id, reply.id) &&
                Objects.equals(content, reply.content) &&
                Objects.equals(videoId, reply.videoId) &&
                Objects.equals(replier, reply.replier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, content, videoId, replier);
    }
}
