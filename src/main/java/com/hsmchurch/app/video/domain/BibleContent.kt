package com.hsmchurch.app.video.domain

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class BibleContent(
        @Enumerated(value = EnumType.STRING)
        @Column(name = "bible_book") private val bibleBook: BibleBook,
        @Column(name = "chapter") private val chapter: Int,
        @Column(name = "start_verse") private val startVerse: Int,
        @Column(name = "end_verse") private val endVerse: Int) {

    init {
        validate(startVerse, endVerse)
    }

    private fun validate(startVerse: Int,
                         endVerse: Int) {
        if (startVerse < 1 || endVerse < 1) {
            throw RuntimeException("0 이하가 올 수 없습니다.")
        }
        if (endVerse < startVerse) {
            throw RuntimeException("끝절이 더 먼저왔습니다.")
        }
    }
}
