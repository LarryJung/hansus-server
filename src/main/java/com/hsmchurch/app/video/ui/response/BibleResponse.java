package com.hsmchurch.app.video.ui.response;

import com.hsmchurch.app.video.domain.BibleBook;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BibleResponse {

    private String bibleBook;
    private BibleBook.TestamentType testamentType;
    private int chapter;
    private int startVerse;
    private int endVerse;

}
