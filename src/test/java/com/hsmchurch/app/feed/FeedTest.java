//package com.hsmchurch.app.feed;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
//import com.hsmchurch.app.video.ui.response.VideoListResponse;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toList;
//
//public class FeedTest {
//
//    @Test
//    public void name() throws JsonProcessingException {
//        final NoticeResponse noticeResponse_1 = NoticeResponse.builder()
//                .id(1L)
//                .title("안녕")
//                .writerId(1L)
//                .writerName("길동")
//                .content("난 길동이다")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        final NoticeResponse noticeResponse_2 = NoticeResponse.builder()
//                .id(1L)
//                .title("안녕")
//                .writerId(1L)
//                .writerName("길동")
//                .content("난 길동이다")
//                .createdAt(LocalDateTime.now().minusDays(3L))
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        final VideoListResponse videoListResponse_1 = VideoListResponse.builder()
//                .id(1L)
//                .title("설교_1")
//                .youtubePublishedAt(LocalDateTime.now().minusDays(1L))
//                .build();
//
//        final VideoListResponse videoListResponse_2 = VideoListResponse.builder()
//                .id(1L)
//                .title("설교_1")
//                .youtubePublishedAt(LocalDateTime.now().minusDays(5L))
//                .build();
//
//        final List<NoticeResponse> noticeResponsePage = Arrays.asList(noticeResponse_1, noticeResponse_2);
//        final List<VideoListResponse> videoListResponsePage = Arrays.asList(videoListResponse_1, videoListResponse_2);
//
//        final List<FeedResponse> noticeFeeds = noticeResponsePage.stream().map(NoticeResponse::toFeed).collect(toList());
//        final List<FeedResponse> videoFeeds = videoListResponsePage.stream().map(VideoListResponse::toFeed).collect(toList());
//        final List<FeedResponse> totalFeeds = Stream.concat(noticeFeeds.stream(), videoFeeds.stream())
//                .sorted(Comparator.comparing(FeedResponse::getCreatedAt).reversed())
//                .collect(toList());
//
//        final Feed feed = new Feed(totalFeeds);
//
//        final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//        final LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
//        final LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(formatter);
//
//        final JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
//        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
//
//        final ObjectMapper objectMapper = new ObjectMapper().registerModule(javaTimeModule);
//        final JsonNode jsonNode = objectMapper.valueToTree(feed);
//        String pretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
//        System.out.println(pretty);
//    }
//}
