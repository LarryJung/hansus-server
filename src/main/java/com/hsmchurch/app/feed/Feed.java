package com.hsmchurch.app.feed;

import lombok.Value;

import java.util.List;

@Value
public class Feed {

    private final List<FeedResponse> feeds;

}
