package com.hsmchurch.app.video.entity.value;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBibleContent is a Querydsl query type for BibleContent
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBibleContent extends BeanPath<BibleContent> {

    private static final long serialVersionUID = -65158181L;

    public static final QBibleContent bibleContent = new QBibleContent("bibleContent");

    public final EnumPath<com.hsmchurch.app.video.entity.value.type.BibleBook> bibleBook = createEnum("bibleBook", com.hsmchurch.app.video.entity.value.type.BibleBook.class);

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

