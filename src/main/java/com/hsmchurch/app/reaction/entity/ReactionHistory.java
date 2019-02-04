package com.hsmchurch.app.reaction.entity;

import com.hsmchurch.app.security.account.entity.Account;
import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.hasOwner;
import com.hsmchurch.app.video.entity.Video;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "reaction_histories")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReactionHistory extends BaseEntity implements hasOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    private ReactionHistory(final Reaction reaction, final Account account, final Video video) {
        this.reaction = reaction;
        this.account = account;
        this.video = video;
    }

    public static ReactionHistory of(final Reaction reaction, final Account account, final Video video) {
        return new ReactionHistory(reaction, account, video);
    }

    @Override
    public boolean isOwner(final Long writerId) {
        return this.account.isYou(writerId);
    }
}
