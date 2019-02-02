package com.hsmchurch.app.video.entity;

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

    private static final long serialVersionUID = -1754347646L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideo video = new QVideo("video");

    public final com.hsmchurch.app.core.QBaseEntity _super = new com.hsmchurch.app.core.QBaseEntity(this);

    public final ListPath<com.hsmchurch.app.video.entity.value.BibleContent, com.hsmchurch.app.video.entity.value.QBibleContent> bibleContents = this.<com.hsmchurch.app.video.entity.value.BibleContent, com.hsmchurch.app.video.entity.value.QBibleContent>createList("bibleContents", com.hsmchurch.app.video.entity.value.BibleContent.class, com.hsmchurch.app.video.entity.value.QBibleContent.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final DatePath<java.time.LocalDate> filmedAt = createDate("filmedAt", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath preacher = createString("preacher");

    public final com.hsmchurch.app.video.entity.value.QThumbNail thumbNail;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final BooleanPath usable = _super.usable;

    public final EnumPath<com.hsmchurch.app.video.entity.value.type.VideoType> videoType = createEnum("videoType", com.hsmchurch.app.video.entity.value.type.VideoType.class);

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
        this.thumbNail = inits.isInitialized("thumbNail") ? new com.hsmchurch.app.video.entity.value.QThumbNail(forProperty("thumbNail")) : null;
    }

}

