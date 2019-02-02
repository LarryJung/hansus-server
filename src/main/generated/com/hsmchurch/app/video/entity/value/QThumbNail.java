package com.hsmchurch.app.video.entity.value;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QThumbNail is a Querydsl query type for ThumbNail
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QThumbNail extends BeanPath<ThumbNail> {

    private static final long serialVersionUID = -5533930L;

    public static final QThumbNail thumbNail = new QThumbNail("thumbNail");

    public final NumberPath<Integer> thumbnailHeight = createNumber("thumbnailHeight", Integer.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final NumberPath<Integer> thumbnailWidth = createNumber("thumbnailWidth", Integer.class);

    public QThumbNail(String variable) {
        super(ThumbNail.class, forVariable(variable));
    }

    public QThumbNail(Path<? extends ThumbNail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThumbNail(PathMetadata metadata) {
        super(ThumbNail.class, metadata);
    }

}

