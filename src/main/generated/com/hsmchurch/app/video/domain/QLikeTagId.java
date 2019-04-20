package com.hsmchurch.app.video.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QLikeTagId is a Querydsl query type for LikeTagId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QLikeTagId extends BeanPath<LikeTagId> {

    private static final long serialVersionUID = -287919066L;

    public static final QLikeTagId likeTagId = new QLikeTagId("likeTagId");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QLikeTagId(String variable) {
        super(LikeTagId.class, forVariable(variable));
    }

    public QLikeTagId(Path<? extends LikeTagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikeTagId(PathMetadata metadata) {
        super(LikeTagId.class, metadata);
    }

}

