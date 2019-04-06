package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.common.config.QuerydslConfiguration;
import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.reply.ui.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.ui.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.video.application.LikeTagService;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
@ActiveProfiles("db-test")
@DataJpaTest
@Import({ReplyService.class, LikeTagService.class, QuerydslConfiguration.class})
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    private static Long ACCOUNT_ID = 1L;
    private static Long VIDEO_ID = 1L;

    @Test
    public void name() {

    }

    //    @Before
//    public void setUp() {
//        String ACCOUNT_NAME = "test-user";
//        replyService.apply(new ReplyApplyRequest(ACCOUNT_ID, ACCOUNT_NAME, VIDEO_ID, "좋습니다.1"));
//        replyService.apply(new ReplyApplyRequest(ACCOUNT_ID, ACCOUNT_NAME, VIDEO_ID, "좋습니다.2"));
//        replyService.apply(new ReplyApplyRequest(ACCOUNT_ID, ACCOUNT_NAME, VIDEO_ID, "좋습니다.3"));
//    }

//    @Test
//    public void deleteExceptionTest_Success() {
//        final Long replyId = replyService.findAllByWriter(1L, null).get(0).getId();
//        final ReplyDeleteRequest replyDeleteRequest = new ReplyDeleteRequest(replyId, ACCOUNT_ID);
//        boolean result = replyService.forceDeleteReply(replyDeleteRequest);
//        assertTrue(result);
//    }
//
//    @Test
//    public void deleteExceptionTest_Fail() {
//        final Long replyId = replyService.findAllByWriter(1L).get(0).getId();
//        final ReplyDeleteRequest replyDeleteRequest = new ReplyDeleteRequest(999L, ACCOUNT_ID);
//        boolean result = replyService.forceDeleteReply(replyDeleteRequest);
//        assertFalse(result);
//    }

//    @Test
//    public void deleteAllByWriterAndVideo_Success() {
//        final TargetVideoRepliesDeleteRequest deleteForm = new TargetVideoRepliesDeleteRequest(VIDEO_ID, ACCOUNT_ID);
//        boolean result = replyService.deleteReplies(deleteForm);
//        assertTrue(result);
//    }
//
//    @Test
//    public void deleteAllByWriterAndVideo_Fail() {
//        final TargetVideoRepliesDeleteRequest deleteForm = new TargetVideoRepliesDeleteRequest(VIDEO_ID, 999L);
//        boolean result = replyService.deleteReplies(deleteForm);
//        assertFalse(result);
//    }
}