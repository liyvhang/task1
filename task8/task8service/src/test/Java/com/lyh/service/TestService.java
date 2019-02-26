package com.lyh.service;

import com.lyh.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//用来指定加载的Spring配置文件的位置，会加载默认的配置文件
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestService {
    @Autowired
    StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);
    @Test
    public void test1(){
        logger.info("开始查询学生信息");
        Long studentId = 9L;
        Student student = studentService.selectStudentKey(studentId);
        logger.info("学生ID为"+studentId+"的个人信息为"+student);
    }
}