package com.hsmchurch.app.video.ui.request;

import com.hsmchurch.app.video.support.DescriptionParseResult;
import com.hsmchurch.app.video.support.DescriptionParser;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class YoutubeVideoInfo {

    private final String id;
    private final String publishedAt;
    private final String title;
    private final String description;
    private final ThumbNailForm thumbNail;

    public DescriptionParseResult parseDescription(final DescriptionParser parser){
        return parser.parse(description);
    }

}
