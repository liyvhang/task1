package com.lyh;

import com.lyh.model.First;
import com.lyh.service.FirstService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
* @param // FIXME: 2018/12/4
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestFirst {
    @Autowired
    FirstService firstService;
    @Test
    public void testFindFirst(){
        List<First> firsts = firstService.findFirst(null,null);
        for (First first : firsts){
            System.out.println(first);
        }
    }
    @Test
    public void testInsert(){
        First record = new First();
        record.setName("架上绘画装置");
        record.setStatus(true);
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("姜子牙");
        firstService.insertSelective(record);
        System.out.println(record.getId());
    }
    @Test
    public void testUpdate(){
        First record = new First();
        record.setName("架上绘画装置");
        record.setStatus(true);
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("姜子牙");
        record.setId((long) 2);
        firstService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
        firstService.deleteByPrimaryKey((long) 1);
    }
    }
