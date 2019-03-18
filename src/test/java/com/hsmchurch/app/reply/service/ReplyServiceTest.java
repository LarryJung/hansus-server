package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.core.config.QuerydslConfiguration;
import com.hsmchurch.app.security.account.entity.Account;
import com.hsmchurch.app.security.account.entity.AccountRepository;
import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteForm;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteForm;
import com.hsmchurch.app.reply.entity.Reply;
import com.hsmchurch.app.reply.entity.repository.ReplyRepository;
import com.hsmchurch.app.security.account.service.AccountService;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.entity.repository.VideoRepository;
import com.hsmchurch.app.video.service.LikeTagService;
import com.hsmchurch.app.video.service.VideoService;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
@ActiveProfiles("db-test")
@DataJpaTest
@Import({ReplyService.class, AccountService.class, VideoService.class, LikeTagService.class, QuerydslConfiguration.class})
public class ReplyServiceTest {

    @Resource
    private ReplyRepository replyRepository;
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private VideoRepository videoRepository;
    @Autowired
    private ReplyService replyService;

    private static Long ACCOUNT_ID = 1L;
    private static Long VIDEO_ID = 1L;

    @Before
    public void setUp() {
        Account sampleAccount = accountRepository.findById(ACCOUNT_ID).get();
        Video sampleVideo = videoRepository.findById(VIDEO_ID).get();

        replyRepository.save(Reply.of(sampleAccount, sampleVideo, "좋습니다1."));
        replyRepository.save(Reply.of(sampleAccount, sampleVideo, "좋습니다2."));
        replyRepository.save(Reply.of(sampleAccount, sampleVideo, "좋습니다3."));
    }

    @Test
    public void deleteExceptionTest() {
        final Long replyId = replyService.findAllByWriter(1L).get(0).getId();
        final ReplyDeleteForm deleteForm = new ReplyDeleteForm(replyId, 1L);
        long result = replyService.deleteReply(deleteForm);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void deleteAllByWriterAndVideo() {
        final TargetVideoRepliesDeleteForm deleteForm = new TargetVideoRepliesDeleteForm(VIDEO_ID, ACCOUNT_ID);
        long result = replyService.deleteReplies(deleteForm);
        assertThat(result).isEqualTo(3);
    }
}