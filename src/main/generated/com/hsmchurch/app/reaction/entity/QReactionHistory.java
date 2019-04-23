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

    public final com.hsmchurch.app.common.QBaseEntity _super = new com.hsmchurch.app.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final QReactionContent reactionContent;

    public final QReactionHistoryId reactionHistoryId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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
        this.reactionContent = inits.isInitialized("reactionContent") ? new QReactionContent(forProperty("reactionContent")) : null;
        this.reactionHistoryId = inits.isInitialized("reactionHistoryId") ? new QReactionHistoryId(forProperty("reactionHistoryId")) : null;
    }

}

