package com.hsmchurch.app.reaction.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QReactionContent is a Querydsl query type for ReactionContent
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReactionContent extends BeanPath<ReactionContent> {

    private static final long serialVersionUID = 1151829835L;

    public static final QReactionContent reactionContent = new QReactionContent("reactionContent");

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public QReactionContent(String variable) {
        super(ReactionContent.class, forVariable(variable));
    }

    public QReactionContent(Path<? extends ReactionContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReactionContent(PathMetadata metadata) {
        super(ReactionContent.class, metadata);
    }

}

