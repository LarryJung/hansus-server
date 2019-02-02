package com.hsmchurch.app.reaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReaction is a Querydsl query type for Reaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReaction extends EntityPathBase<Reaction> {

    private static final long serialVersionUID = -2000236210L;

    public static final QReaction reaction = new QReaction("reaction");

    public final com.hsmchurch.app.core.QBaseEntity _super = new com.hsmchurch.app.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public QReaction(String variable) {
        super(Reaction.class, forVariable(variable));
    }

    public QReaction(Path<? extends Reaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReaction(PathMetadata metadata) {
        super(Reaction.class, metadata);
    }

}

