package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.domain.BibleBook;
import com.hsmchurch.app.video.domain.BibleContent;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class YoutubeDescriptionParser implements DescriptionParser{

    @Override
    public DescriptionParseResult parse(final String description) {
        final BibleContent bibleContent1 = new BibleContent(BibleBook.GENESIS, 1, 1, 10);
        final BibleContent bibleContent2 = new BibleContent(BibleBook.EXODUS, 2, 1, 15);
        List<BibleContent> bibleContents = Arrays.asList(bibleContent1, bibleContent2);
        return new DescriptionParseResult(LocalDate.now(), "최성광", bibleContents);
    }
}
