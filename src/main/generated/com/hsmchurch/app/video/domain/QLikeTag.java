package com.hsmchurch.app.video.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeTag is a Querydsl query type for LikeTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikeTag extends EntityPathBase<LikeTag> {

    private static final long serialVersionUID = -1211471445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeTag likeTag = new QLikeTag("likeTag");

    public final com.hsmchurch.app.common.QBaseEntity _super = new com.hsmchurch.app.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final QLikeTagId likeTagId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public QLikeTag(String variable) {
        this(LikeTag.class, forVariable(variable), INITS);
    }

    public QLikeTag(Path<? extends LikeTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeTag(PathMetadata metadata, PathInits inits) {
        this(LikeTag.class, metadata, inits);
    }

    public QLikeTag(Class<? extends LikeTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.likeTagId = inits.isInitialized("likeTagId") ? new QLikeTagId(forProperty("likeTagId")) : null;
    }

}

