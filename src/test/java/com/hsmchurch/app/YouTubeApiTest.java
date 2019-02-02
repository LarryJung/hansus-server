package com.hsmchurch.app;

import com.hsmchurch.app.video.GlobalValue;
import com.hsmchurch.app.video.api.dto.request.YoutubeForm;
import com.hsmchurch.app.video.support.YoutubeCrawler;
import org.json.JSONException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GlobalValue.class, YoutubeCrawler.class})
public class YouTubeApiTest {

    @Test
    public void youtubeCrawlingTest() throws JSONException {
        final List<YoutubeForm> result = YoutubeCrawler.collectInfos(null, new ArrayList<>());
        System.out.println(result.size());
    }

}
