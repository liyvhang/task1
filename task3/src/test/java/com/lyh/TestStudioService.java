package com.lyh;

import com.lyh.model.Studio;
import com.lyh.service.StudioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestStudioService {
    @Autowired
    StudioService studioService;
    @Test
    public void testInsert(){
        Studio record = new Studio();
        record.setName("工作室简介");
        record.setStatus(false);
        record.setPicture("图片");
        record.setAbout("我是富文本编辑器");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("哪吒");
        studioService.insertSelective(record);
        System.out.println(record.getId());
    }
    @Test
    public void testUpdate(){
        Studio record = new Studio();
        record.setName("工作室简介");
        record.setStatus(true);
        record.setPicture("图片");
        record.setAbout("我是富文本编辑器");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("哪吒");
        record.setId((long) 2);
        studioService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
        studioService.deleteByPrimaryKey((long) 1);
    }

    @Test
    public void testFindStudio(){
    List<Studio> studios = studioService.findStudio(null,null);
    for (Studio studio:studios){
        System.out.println(studio);
    }
    }
}
