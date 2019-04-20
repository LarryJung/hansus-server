package com.hsmchurch.app.video.domain

import com.hsmchurch.app.video.domain.BibleBook.TestamentType.구약

enum class BibleBook constructor(
        val korean: String,
        val abbreviation: String,
        val testamentType: TestamentType
) {
    GENESIS("창세기", "창", 구약),
    EXODUS("출애굽기", "출", 구약),
    LEVITICUS("레위기", "레", 구약),
    NUMBERS("민수기", "민", 구약),
    DEUTERONOMY("신명기", "신", 구약),
    JOSHUA("여호수아", "수", 구약),
    JUDGES("사사기", "삿", 구약),
    RUTH("룻기", "룻", 구약),
    SAMUEL_1("사무엘상", "삼상", 구약),
    SAMUEL_2("사무엘하", "삼하", 구약),
    KINGS_1("열왕기상", "왕상", 구약),
    KINGS_2("열왕기하", "왕하", 구약),
    CHRONICLES_1("역대상", "대상", 구약),
    CHRONICLES_2("역대하", "대하", 구약),
    EZRA("에스라", "스", 구약),
    NEHEMIAH("느헤미야", "느", 구약),
    ESTHER("에스더", "에", 구약),
    JOB("욥기", "욥", 구약),
    PSALMS("시편", "시", 구약),
    PROVERBS("잠언", "잠", 구약);

    enum class TestamentType {
        구약, 신약
    }
}
