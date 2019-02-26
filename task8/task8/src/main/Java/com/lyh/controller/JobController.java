package com.lyh.controller;

import com.lyh.entity.Job;
import com.lyh.service.JobService;

import com.lyh.service.StudentService;
import com.lyh.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class JobController {
    @Autowired
//    private JobService jobService;
    RedisUtil redisUtil;

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    JobService jobService = (JobService)applicationContext.getBean("jobRMIClient");

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    @RequestMapping("/job")
    public String job(Model model){
        logger.info("-----job方法被调用------");
        List<Job> jobs = jobService.selectJob();
        logger.info("job结果为："+jobs);
        model.addAttribute("jobs",jobs);
        return "job";
    }
    @RequestMapping("/u/partner")
    public String partner(){
        logger.info("-----进入关于页面----");
        return "partner";
    }
}
