package com.hsmchurch.app.reaction.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class ReactionHistoryId implements Serializable {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "reaction_id")
    private Long reactionId;

    public ReactionHistoryId(final Long accountId,
                             final Long videoId,
                             final Long reactionId) {
        this.accountId = accountId;
        this.videoId = videoId;
        this.reactionId = reactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionHistoryId historyId = (ReactionHistoryId) o;
        return Objects.equals(accountId, historyId.accountId) &&
                Objects.equals(videoId, historyId.videoId) &&
                Objects.equals(reactionId, historyId.reactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, videoId, reactionId);
    }

    public boolean isOwner(Long accountId) {
        return this.accountId.equals(accountId);
    }
}
