package com.hsmchurch.app.video.domain;

import lombok.Getter;

import static com.hsmchurch.app.video.domain.BibleBook.TestamentType.구약;
import static com.hsmchurch.app.video.domain.BibleBook.TestamentType.신약;

@Getter
public enum BibleBook {

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
    PROVERBS("잠언", "잠", 구약),
    ECCLESIASTES("전도서", "전", 구약),
    SONG_OF_SONGS("아가", "아", 구약),
    ISAIAH("이사야", "사", 구약),
    JEREMIAH("예레미야", "렘", 구약),
    Lamentations("예레미야애가", "애", 구약),
    Ezekiel("에스겔", "겔", 구약),
    Daniel("다니엘", "단", 구약),
    Hosea("호세아", "호", 구약),
    Joel("요엘", "욜", 구약),
    Amos("아모스", "암", 구약),
    Obadiah("오바다", "옵", 구약),
    Jonah("요나", "욘", 구약),
    Micah("미가", "미", 구약),
    Nahum("나훔", "나", 구약),
    Habakkuk("하박국", "합", 구약),
    Zephaniah("스바냐", "습", 구약),
    Haggai("학개", "학", 구약),
    Zechariah("스가랴", "슥", 구약),
    Malachi("말라기", "말", 구약),
    Matthew("마태복음", "마", 신약),
    Mark("마가복음", "막", 신약),
    Luke("누가복음", "눅", 신약),
    John("요한복음", "요", 신약),
    The_Acts("사도행전", "행", 신약),
    Romans("로마서", "롬", 신약),
    Corinthians_1("고린도전서", "고전", 신약),
    Corinthians_2("고린도후서", "고후", 신약),
    Galatians("갈라디아서", "갈", 신약),
    Ephesians("에베소서", "엡", 신약),
    Philippians("빌립보서", "빌", 신약),
    Colossians("골로새서", "골", 신약),
    Thessalonians_1("데살로니가전서", "살전", 신약),
    Thessalonians_2("데살로니가후서", "살후", 신약),
    Timothy_1("디모데전서", "딤전", 신약),
    Timothy_2("디모데후서", "딤후", 신약),
    Titus("디도서", "딛", 신약),
    Philemon("빌레몬서", "몬", 신약),
    The_Hebrews("히브리서", "히", 신약),
    James("야고보서", "약", 신약),
    Peter_1("베드로전서", "벧전", 신약),
    Peter_2("베드로후서", "벧후", 신약),
    John_1("요한1서", "요1", 신약),
    John_2("요한2서", "요2", 신약),
    John_3("요한3서", "요3", 신약),
    Jude("유다서", "유", 신약),
    Revelation("요한계시록", "계", 신약);

    private String korean;
    private String abbreviation;
    private TestamentType testamentType;

    BibleBook(String korean, String abbreviation, TestamentType testamentType) {
        this.korean = korean;
        this.abbreviation = abbreviation;
        this.testamentType = testamentType;
    }

    public enum TestamentType {
        구약, 신약
    }

    public static BibleBook fromString(String text) {
        for (BibleBook b : BibleBook.values()) {
            if (b.korean.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }


}
