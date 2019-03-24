package com.hsmchurch.app.video.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QThumbnail is a Querydsl query type for Thumbnail
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QThumbnail extends BeanPath<Thumbnail> {

    private static final long serialVersionUID = 1216951316L;

    public static final QThumbnail thumbnail = new QThumbnail("thumbnail");

    public final NumberPath<Integer> thumbnailHeight = createNumber("thumbnailHeight", Integer.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final NumberPath<Integer> thumbnailWidth = createNumber("thumbnailWidth", Integer.class);

    public QThumbnail(String variable) {
        super(Thumbnail.class, forVariable(variable));
    }

    public QThumbnail(Path<? extends Thumbnail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThumbnail(PathMetadata metadata) {
        super(Thumbnail.class, metadata);
    }

}

