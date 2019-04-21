package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.ui.request.ThumbNailForm;
import com.hsmchurch.app.video.ui.request.YoutubeVideoInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class YoutubeCrawler {

    @Value("${youtube.key}")
    private String YOUTUBE_CLIENT_KEY;
    @Value("${youtube.url}")
    private String YOUTUBE_API_URL;

    @Value("${youtube.channel-id}")
    private String YOUTUBE_CHANNEL_ID;

    public List<YoutubeVideoInfo> collectInfos(final String nextToken,
                                               final List<YoutubeVideoInfo> youtubeVideoInfos) throws JSONException {
        final JSONObject videoListJSON = callApiResponse(nextToken);
        final List<YoutubeVideoInfo> newList = new ArrayList<>();
        final JSONArray items = videoListJSON.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            final JSONObject item = items.getJSONObject(i);

            if (item.getJSONObject("id").optString("videoId").isEmpty()) {
                continue;
            }

            final YoutubeVideoInfo newOne = new YoutubeVideoInfo(
                    item.getJSONObject("id").optString("videoId"),
                    item.getJSONObject("snippet").optString("publishedAt"),
                    item.getJSONObject("snippet").optString("title"),
                    item.getJSONObject("snippet").optString("description"),
                    new ThumbNailForm(
                            item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").optString("url"),
                            item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getInt("width"),
                            item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getInt("height")
                    )
            );
            newList.add(newOne);
        }

        try {
            final String nextPageToken = videoListJSON.getString("nextPageToken");

            return collectInfos(nextPageToken, Stream.concat(youtubeVideoInfos.stream(), newList.stream()).collect(toList()));
        } catch (JSONException e) {
            return Stream.concat(youtubeVideoInfos.stream(), newList.stream()).collect(toList());
        }
    }

    private JSONObject callApiResponse(final String nextToken) throws JSONException {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        final UriComponentsBuilder builder = getBuilder();
        if (nextToken != null) {
            builder.queryParam("pageToken", nextToken);
        }
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        final HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        return new JSONObject(response.getBody());
    }

    private UriComponentsBuilder getBuilder() {
        return UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("key", YOUTUBE_CLIENT_KEY)
                .queryParam("channelId", YOUTUBE_CHANNEL_ID)
                .queryParam("part", "snippet")
                .queryParam("maxResults", 50)
                .queryParam("order", "date");
    }

}
