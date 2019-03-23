package com.hsmchurch.app.video.entity.value;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QThumbNail is a Querydsl query type for Thumbnail
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QThumbNail extends BeanPath<Thumbnail> {

    private static final long serialVersionUID = -5533930L;

    public static final QThumbNail thumbNail = new QThumbNail("thumbNail");

    public final NumberPath<Integer> thumbnailHeight = createNumber("thumbnailHeight", Integer.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final NumberPath<Integer> thumbnailWidth = createNumber("thumbnailWidth", Integer.class);

    public QThumbNail(String variable) {
        super(Thumbnail.class, forVariable(variable));
    }

    public QThumbNail(Path<? extends Thumbnail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThumbNail(PathMetadata metadata) {
        super(Thumbnail.class, metadata);
    }

}

