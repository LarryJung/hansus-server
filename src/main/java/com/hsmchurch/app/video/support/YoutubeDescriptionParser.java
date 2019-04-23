package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.domain.BibleBook;
import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.VideoType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class YoutubeDescriptionParser implements DescriptionParser {

    @Override
    public DescriptionParseResult parse(final String description) {
        final String[] strings = description.split("\\|");
        final String filmedAt = strings[0].split(":")[1].trim();
        final VideoType videoType = VideoType.valueOf(strings[1].split(":")[1].trim().toUpperCase());
        if (videoType != VideoType.PREACH) {
            return new DescriptionParseResult(LocalDate.parse(filmedAt), Optional.empty(), Optional.empty(), videoType);
        }
        final String preacher = strings[2].split(":")[1].trim();
        final String[] words = strings[3].split(":")[1].trim().split(",");
        final List<BibleContent> bibleContents = Arrays.stream(words).map(this::parseBibleContent).collect(toList());
        return new DescriptionParseResult(LocalDate.parse(filmedAt), Optional.of(preacher), Optional.of(bibleContents), videoType);
    }

    private BibleContent parseBibleContent(final String word) {
        final String[] parts = word.trim().replaceAll("[\\[\\]]", "").split(" ");
        final BibleBook bibleBook = BibleBook.fromString(parts[0]);
        final int chapter = Integer.parseInt(parts[1].replace("장", ""));
        final String[] verses = parts[2].split("-");
        return new BibleContent(bibleBook, chapter, Integer.parseInt(verses[0]), Integer.parseInt(verses[1]));
    }
}
