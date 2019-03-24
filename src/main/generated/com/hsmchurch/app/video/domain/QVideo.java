package com.hsmchurch.app.video.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideo is a Querydsl query type for Video
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVideo extends EntityPathBase<Video> {

    private static final long serialVersionUID = 401263523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideo video = new QVideo("video");

    public final com.hsmchurch.app.common.QBaseEntity _super = new com.hsmchurch.app.common.QBaseEntity(this);

    public final ListPath<BibleContent, QBibleContent> bibleContents = this.<BibleContent, QBibleContent>createList("bibleContents", BibleContent.class, QBibleContent.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final DatePath<java.time.LocalDate> filmedAt = createDate("filmedAt", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath preacher = createString("preacher");

    public final QThumbnail thumbnail;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public final EnumPath<VideoType> videoType = createEnum("videoType", VideoType.class);

    public final StringPath youtubeId = createString("youtubeId");

    public final DateTimePath<java.time.LocalDateTime> youtubePublishedAt = createDateTime("youtubePublishedAt", java.time.LocalDateTime.class);

    public QVideo(String variable) {
        this(Video.class, forVariable(variable), INITS);
    }

    public QVideo(Path<? extends Video> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideo(PathMetadata metadata, PathInits inits) {
        this(Video.class, metadata, inits);
    }

    public QVideo(Class<? extends Video> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.thumbnail = inits.isInitialized("thumbnail") ? new QThumbnail(forProperty("thumbnail")) : null;
    }

}

