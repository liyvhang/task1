package com;

import com.lyh.entity.Student;
import com.lyh.service.StudentService;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//用来指定加载的Spring配置文件的位置，会加载默认的配置文件
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestMemcached {
    private static final Logger logger = LoggerFactory.getLogger(TestMemcached.class);
    @Autowired
    StudentService studentService;
    @Test
    public  void test1() {

        /*初始化SockIOPool，管理memcached的连接池*/
        String[] servers = { "127.0.0.1:11211" };

        SockIOPool pool = SockIOPool.getInstance();

        pool.setServers(servers);

        pool.setFailover(true);

        pool.setInitConn(10);

        pool.setMinConn(5);

        pool.setMaxConn(250);

        pool.setMaintSleep(30);

        pool.setNagle(false);

        pool.setSocketTO(3000);

        pool.setAliveCheck(true);

        pool.initialize();

        MemCachedClient memCachedClient = new MemCachedClient();

        memCachedClient.set("test1","im value");

        logger.info("设置缓存结果为："+memCachedClient.get("test1"));
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        MemCachedClient memCachedClient = (MemCachedClient) applicationContext.getBean("memcachedClient");
        memCachedClient.set("test3","im value2");
        logger.info((String) memCachedClient.get("test3"));
    }
    @Test
    public void selectId(){
        Student student =studentService.selectStudentKey(1L);
        System.out.println("student = " + student);
    }
    @Test
    public void test3(){
        Student student = new Student();
        student.setImg("/out/img/man1.png");
        student.setName("刘德华");
        student.setPosition("技术总监");
        student.setIntro("简介");
        student.setSalary(10000L);
        student.setEmploymentStatus(false);
        student.setExcellenceDegree(true);
        student.setCreateAt(System.currentTimeMillis());
        student.setUpdateAt(System.currentTimeMillis());
        student.setUpdateBy("李雨航");
        student.setProfessionName("ui");
        for (int i = 0; i <50 ; i++) {
            for (int j = 0; j <20; j++) {
                studentService.addStudent(student);
            }

        }

    }
}
