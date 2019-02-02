package com.hsmchurch.app.video.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikeTag is a Querydsl query type for LikeTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikeTag extends EntityPathBase<LikeTag> {

    private static final long serialVersionUID = 1715399114L;

    public static final QLikeTag likeTag = new QLikeTag("likeTag");

    public final com.hsmchurch.app.core.QBaseEntity _super = new com.hsmchurch.app.core.QBaseEntity(this);

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QLikeTag(String variable) {
        super(LikeTag.class, forVariable(variable));
    }

    public QLikeTag(Path<? extends LikeTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikeTag(PathMetadata metadata) {
        super(LikeTag.class, metadata);
    }

}

