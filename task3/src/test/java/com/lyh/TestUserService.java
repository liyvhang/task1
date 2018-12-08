package com.lyh;

import com.lyh.model.User;
import com.lyh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestUserService {
    @Autowired
    UserService userService;

    @Test
    public void testInsert(){
        User record = new User();
        record.setUserName("塞尚");
        record.setPassword("2222");
        record.setRole("管理员");
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setFounder("徐悲鸿");
        userService.insertSelective(record);
        System.out.println(record.getId());
    }
    @Test
    public void updateByPrimaryKeySelective(){
        User record = new User();
        record.setUserName("毕加索");
        record.setPassword("22221");
        record.setRole("运营");
//        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        record.setFounder("admin");
        record.setId((long) 2);
        userService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testDelete(){
        userService.deleteByPrimaryKey((long) 2);
    }
    @Test
    public void testFindUser(){
     List<User> users= userService.findUser(null,"市场");
    for(User user : users){
        System.out.println(user);
    }
    }
}
