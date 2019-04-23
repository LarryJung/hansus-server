package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.domain.BibleBook;
import com.hsmchurch.app.video.domain.BibleContent;
import com.hsmchurch.app.video.domain.VideoType;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class YoutubeDescriptionParserTest {

    @Test
    public void parse() {
        final BibleContent bibleContent1 = new BibleContent(BibleBook.GENESIS, 1, 1, 10);
        final BibleContent bibleContent2 = new BibleContent(BibleBook.EXODUS, 2, 1, 15);
        List<BibleContent> bibleContents = Arrays.asList(bibleContent1, bibleContent2);
        final DescriptionParseResult answer =  new DescriptionParseResult(LocalDate.of(2019, 4, 21), Optional.of("최성광"), Optional.of(bibleContents), VideoType.PREACH);

        // 유투브 description 에 길이 제한이 있다.
        final String youtubeTitle = "filmed at: 2019-04-21 | type: preach | preacher: 최성광 | 말씀: [창세기 1장 1-10], [출애굽기 2장 1-15]";

        final YoutubeDescriptionParser parser = new YoutubeDescriptionParser();
        final DescriptionParseResult result = parser.parse(youtubeTitle);

        assertThat(result).isEqualTo(answer);
    }
}