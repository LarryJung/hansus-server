package com.hsmchurch.app.reaction.entity;

import com.hsmchurch.app.common.BaseEntity;
import com.hsmchurch.app.common.HasOwner;
import com.hsmchurch.app.video.ui.request.ReactionApplyRequest;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "reaction_histories")
@Entity
public class ReactionHistory extends BaseEntity implements HasOwner {

    @EmbeddedId
    private ReactionHistoryId reactionHistoryId;

    @Embedded
    private ReactionContent reactionContent;

    private ReactionHistory(final ReactionHistoryId reactionHistoryId,
                            final ReactionContent reactionContent) {
        this.reactionHistoryId = reactionHistoryId;
        this.reactionContent = reactionContent;
    }

    public static ReactionHistory of(final ReactionApplyRequest applyForm) {
        final ReactionHistoryId historyId = new ReactionHistoryId(applyForm.getAccountId(), applyForm.getVideoId(), applyForm.getReactionId());
        final ReactionContent content = new ReactionContent(applyForm.getReactionImageUrl(), applyForm.getReactionName());
        return new ReactionHistory(historyId, content);
    }

    @Override
    public boolean isOwner(final Long accountId) {
        return this.reactionHistoryId.isOwner(accountId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReactionHistory that = (ReactionHistory) o;
        return Objects.equals(reactionHistoryId, that.reactionHistoryId) &&
                Objects.equals(reactionContent, that.reactionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reactionHistoryId, reactionContent);
    }

    public void cancel() {
        markAsDeleted();
    }
}
