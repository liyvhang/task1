package com.lyh;

import com.lyh.model.Comment;
import com.lyh.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestComment {
    @Autowired
    CommentService commentService;
@Test
    public void testFind(){
    List<Comment> comments = commentService.findComment(null,null);
    for(Comment comment : comments){
        System.out.println(comment);
    }
}
    @Test
    public void testInsert(){
    Comment record = new Comment();
    record.setTitle("艺术家简介");
    record.setStatus((short) 1);
    record.setContent("留言内容");
    record.setCreateAt(System.currentTimeMillis());
    record.setUpdateAt(System.currentTimeMillis());
    record.setUpdateBy("姜子牙");
    commentService.insertSelective(record);
    }
    @Test
    public void testUpdate(){
        Comment record = new Comment();
        record.setTitle("艺术家简介");
        record.setStatus((short) 1);
        record.setContent("留言内容");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("姜子牙");
        record.setId((long) 2);
        commentService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
    commentService.deleteByPrimaryKey((long) 1);
    }
}
