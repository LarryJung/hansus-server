package com.hsmchurch.app.video.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class BibleContent {

    @Column(name = "bible_book")
    @Enumerated(value = EnumType.STRING)
    private final BibleBook bibleBook;

    @Column(name = "chapter")
    private final int chapter;

    @Column(name = "start_verse")
    private final int startVerse;

    @Column(name = "end_verse")
    private final int endVerse;

    public BibleContent(final BibleBook bibleBook,
                        final int chapter,
                        final int startVerse,
                        final int endVerse) {
        validate(startVerse, endVerse);
        this.bibleBook = bibleBook;
        this.chapter = chapter;
        this.startVerse = startVerse;
        this.endVerse = endVerse;
    }

    private void validate(final int startVerse,
                          final int endVerse) {
        if (startVerse < 1 || endVerse < 1) {
            throw new RuntimeException("0 이하가 올 수 없습니다.");
        }
        if (endVerse < startVerse) {
            throw new RuntimeException("끝절이 더 먼저왔습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibleContent that = (BibleContent) o;
        return chapter == that.chapter &&
                startVerse == that.startVerse &&
                endVerse == that.endVerse &&
                bibleBook == that.bibleBook;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bibleBook, chapter, startVerse, endVerse);
    }
}
