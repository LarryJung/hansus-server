package com.hsmchurch.app.reply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReplier is a Querydsl query type for Replier
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReplier extends BeanPath<Replier> {

    private static final long serialVersionUID = 51926015L;

    public static final QReplier replier = new QReplier("replier");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QReplier(String variable) {
        super(Replier.class, forVariable(variable));
    }

    public QReplier(Path<? extends Replier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReplier(PathMetadata metadata) {
        super(Replier.class, metadata);
    }

}

