package com.hsmchurch.app.video.support

import com.hsmchurch.app.video.domain.BibleBook
import com.hsmchurch.app.video.domain.BibleContent
import java.time.LocalDate
import java.util.*

class YoutubeDescriptionParser : DescriptionParser {

    override fun parse(description: String): DescriptionParseResult {
        val bibleContent1 = BibleContent(BibleBook.GENESIS, 1, 1, 10)
        val bibleContent2 = BibleContent(BibleBook.EXODUS, 2, 1, 15)
        val bibleContents = Arrays.asList(bibleContent1, bibleContent2)
        return DescriptionParseResult(LocalDate.now(), "최성광", bibleContents)
    }
}
