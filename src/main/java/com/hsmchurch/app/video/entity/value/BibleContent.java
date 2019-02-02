package com.hsmchurch.app.video.entity.value;

import com.hsmchurch.app.video.entity.value.type.BibleBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Value
@Embeddable
public class BibleContent {

    @Column(name = "bible_book")
    @Enumerated(value = EnumType.STRING)
    private final BibleBook bibleBook;

    private final int chapter;

    private final int startVerse;

    private final int endVerse;

}
