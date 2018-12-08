package com.lyh;

import com.lyh.model.Reply;
import com.lyh.service.ReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestReply {
    @Autowired
    ReplyService replyService;
    @Test
    public void testFindReply(){
        List<Reply> replies= replyService.findReply(null,null);
        for (Reply reply : replies){
            System.out.println(reply);
        }
    }
    @Test
    public void testInsert(){
        Reply record = new Reply();
        record.setNickname("懵逼");
        record.setCommentId((long) 2);
        record.setMessageContent("回复内容");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        replyService.insertSelective(record);
        System.out.println(record);
    }
    @Test
    public void testUpdate(){
        Reply record = new Reply();
        record.setNickname("懵逼");
        record.setCommentId((long) 2);
        record.setMessageContent("回复内容");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setId((long) 2);
        replyService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
        replyService.deleteByPrimaryKey((long) 1);
    }
}
