package com.hsmchurch.app.reply.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QReplier is a Querydsl query type for Replier
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReplier extends BeanPath<Replier> {

    private static final long serialVersionUID = 1420022752L;

    public static final QReplier replier = new QReplier("replier");

    public final NumberPath<Long> id = createNumber("id", Long.class);

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

