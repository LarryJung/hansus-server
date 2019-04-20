package com.hsmchurch.app.video.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QBibleContent is a Querydsl query type for BibleContent
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBibleContent extends BeanPath<BibleContent> {

    private static final long serialVersionUID = -665211395L;

    public static final QBibleContent bibleContent = new QBibleContent("bibleContent");

    public final EnumPath<BibleBook> bibleBook = createEnum("bibleBook", BibleBook.class);

    public final NumberPath<Integer> chapter = createNumber("chapter", Integer.class);

    public final NumberPath<Integer> endVerse = createNumber("endVerse", Integer.class);

    public final NumberPath<Integer> startVerse = createNumber("startVerse", Integer.class);

    public QBibleContent(String variable) {
        super(BibleContent.class, forVariable(variable));
    }

    public QBibleContent(Path<? extends BibleContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBibleContent(PathMetadata metadata) {
        super(BibleContent.class, metadata);
    }

}

