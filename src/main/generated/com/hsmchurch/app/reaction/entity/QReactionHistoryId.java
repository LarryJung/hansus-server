package com.hsmchurch.app.reaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReactionHistoryId is a Querydsl query type for ReactionHistoryId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReactionHistoryId extends BeanPath<ReactionHistoryId> {

    private static final long serialVersionUID = 939523873L;

    public static final QReactionHistoryId reactionHistoryId = new QReactionHistoryId("reactionHistoryId");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> reactionId = createNumber("reactionId", Long.class);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QReactionHistoryId(String variable) {
        super(ReactionHistoryId.class, forVariable(variable));
    }

    public QReactionHistoryId(Path<? extends ReactionHistoryId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReactionHistoryId(PathMetadata metadata) {
        super(ReactionHistoryId.class, metadata);
    }

}

