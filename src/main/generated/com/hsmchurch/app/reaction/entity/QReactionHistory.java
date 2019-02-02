package com.hsmchurch.app.reaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReactionHistory is a Querydsl query type for ReactionHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReactionHistory extends EntityPathBase<ReactionHistory> {

    private static final long serialVersionUID = 1127233382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReactionHistory reactionHistory = new QReactionHistory("reactionHistory");

    public final com.hsmchurch.app.core.QBaseEntity _super = new com.hsmchurch.app.core.QBaseEntity(this);

    public final com.hsmchurch.app.account.entity.QAccount account;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReaction reaction;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public final com.hsmchurch.app.video.entity.QVideo video;

    public QReactionHistory(String variable) {
        this(ReactionHistory.class, forVariable(variable), INITS);
    }

    public QReactionHistory(Path<? extends ReactionHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReactionHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReactionHistory(PathMetadata metadata, PathInits inits) {
        this(ReactionHistory.class, metadata, inits);
    }

    public QReactionHistory(Class<? extends ReactionHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.hsmchurch.app.account.entity.QAccount(forProperty("account")) : null;
        this.reaction = inits.isInitialized("reaction") ? new QReaction(forProperty("reaction")) : null;
        this.video = inits.isInitialized("video") ? new com.hsmchurch.app.video.entity.QVideo(forProperty("video"), inits.get("video")) : null;
    }

}

