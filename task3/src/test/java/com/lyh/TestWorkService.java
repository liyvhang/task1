package com.lyh;

import com.lyh.model.Work;
import com.lyh.service.WorkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestWorkService {
    @Autowired
    WorkService workService;
    @Test
    public void testFindWork(){
        List<Work> works = workService.findWork(null,null);
        for(Work work : works){
            System.out.println(work);
        }
    }
    @Test
    public void testInsert(){
    Work record = new Work();
    record.setName("梵高的老舅");
    record.setStatus((short) 1);
    record.setFirstId((long) 2);
    record.setSecondId((long) 4);
    record.setFirstName("架上绘画装置");
    record.setSecondName("绘画");
    record.setIntroduction("作品简介");
    record.setThumbnail("缩略图");
    record.setUrl("http://web-ssm.com/1.jpg");
    record.setContent("介绍文章");
    record.setPicture("图片");
    record.setCreateAt(System.currentTimeMillis());
    record.setUpdateAt(System.currentTimeMillis());
    record.setUpdateBy("杨戬");
    workService.insertSelective(record);
        System.out.println(record);
    }
    @Test
    public void testUpdate(){
        Work record = new Work();
        record.setName("梵高的老舅");
        record.setStatus((short) 1);
        record.setFirstId((long) 2);
        record.setSecondId((long) 4);
        record.setFirstName("架上绘画装置");
        record.setSecondName("绘画");
        record.setIntroduction("作品简介");
        record.setThumbnail("缩略图");
        record.setUrl("www.xxxx.com");
        record.setContent("介绍文章");
        record.setPicture("图片");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("杨戬");
        record.setId((long) 1);
        workService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
        workService.deleteByPrimaryKey((long) 1);
    }
}
