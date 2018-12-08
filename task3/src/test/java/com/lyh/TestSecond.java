package com.lyh;

import com.lyh.model.Second;
import com.lyh.service.SecondService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestSecond {
    @Autowired
    SecondService secondService;
    @Test
    public void testFindSecond(){
        List<Second> seconds = secondService.findSecond(null,null);
        for (Second second : seconds){
            System.out.println(second);
        }
    }
    @Test
    public void testInsert(){
        Second record = new Second();
        record.setName("垂钓者空间");
        record.setStatus((short) 2);
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("哪吒");
        record.setFirstName("架上绘画装置");
        record.setFirstId((long) 2);
        secondService.insertSelective(record);
        System.out.println(record);
    }
    @Test
    public void testUpdate(){
        Second record = new Second();
        record.setName("垂钓者空间");
        record.setStatus((short) 2);
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setUpdateBy("哪吒");
        record.setFirstName("架上绘画装置");
        record.setFirstId((long) 2);
        record.setId((long) 3);
    }
    @Test
    public void testDelete(){
        secondService.deleteByPrimaryKey((long) 3);
    }
}
