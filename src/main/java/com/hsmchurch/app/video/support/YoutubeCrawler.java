package com.hsmchurch.app.video.support;

import com.hsmchurch.app.video.GlobalValue;
import com.hsmchurch.app.video.api.dto.request.ThumbNailForm;
import com.hsmchurch.app.video.api.dto.request.YoutubeForm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class YoutubeCrawler {

    public static List<YoutubeForm> collectInfos(final String nextToken, final List<YoutubeForm> youtubeForms) throws JSONException {
        System.out.println("key : " + GlobalValue.YOUTUBE_CLIENT_KEY);
        System.out.println("url " + GlobalValue.YOUTUBE_API_URL);
        System.out.println("channel :" + GlobalValue.YOUTUBE_CHANNEL_ID);
        final JSONObject videoListJSON = callApiResponse(nextToken);
        final List<YoutubeForm> newList = new ArrayList<>();
        final JSONArray items = videoListJSON.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {

            final JSONObject item = items.getJSONObject(i);

            if (item.getJSONObject("id").optString("videoId").isEmpty()) {
                continue;
            }

            final YoutubeForm newOne = YoutubeForm.builder()
                    .id(item.getJSONObject("id").optString("videoId"))
                    .publishedAt(item.getJSONObject("snippet").optString("publishedAt"))
                    .title(item.getJSONObject("snippet").optString("title"))
                    .description(item.getJSONObject("snippet").optString("description"))
                    .thumbNail(ThumbNailForm.builder()
                            .thumbnailUrl(item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").optString("url"))
                            .thumbnailWidth(item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getInt("width"))
                            .thumbnailHeight(item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getInt("height"))
                            .build()
                    )
                    .build();

            newList.add(newOne);
        }

        try {
            final String nextPageToken = videoListJSON.getString("nextPageToken");
            return collectInfos(nextPageToken, Stream.concat(youtubeForms.stream(), newList.stream()).collect(toList()));
        } catch (JSONException e) {
            return Stream.concat(youtubeForms.stream(), newList.stream()).collect(toList());
        }
    }

    private static JSONObject callApiResponse(final String nextToken) throws JSONException {
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

    private static UriComponentsBuilder getBuilder() {
        return UriComponentsBuilder.fromHttpUrl(GlobalValue.YOUTUBE_API_URL)
                .queryParam("key", GlobalValue.YOUTUBE_CLIENT_KEY)
                .queryParam("channelId", GlobalValue.YOUTUBE_CHANNEL_ID)
                .queryParam("part", "snippet")
                .queryParam("maxResults", 50)
                .queryParam("order", "date");
    }

}