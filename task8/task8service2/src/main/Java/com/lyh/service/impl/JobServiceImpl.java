package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.entity.Job;
import com.lyh.service.JobService;
import com.lyh.utils.RedisUtil;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private JobDao dao;

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Override
    public List<Job> findDevelopmentDirection(String developmentDirection) {
        return dao.findDevelopmentDirection(developmentDirection);
    }

    @Override
    public List<Job> selectJob() {
        List<Job> jobs;
        if (redisUtil.get("jobs") == null){
            logger.info("职业类表没有缓存，开始设置缓存 ");
            jobs = dao.selectJob();
            redisUtil.set("jobs",jobs,60*60);
        }else {
            logger.info("职业类表已有缓存，开始读取缓存 ");
            jobs = (List<Job>)redisUtil.get("jobs");
            logger.info("职业类表数据为："+jobs);
        }
        return jobs;
    }

    @Override
    public Job getId(Long id) {
        return dao.getId(id);
    }

    @Override
    public int addJob(Job job) {
        return dao.addJob(job);
    }

    @Override
    public int deleteJob(Long id) {
        return dao.deleteJob(id);
    }

    @Override
    public int updateJob(Job job) {
        //return dao.updateJobNum(job);
        return 0;
    }

    @Override
    public int updateJobNum(String professionName) {
        return dao.updateJobNum(professionName);
    }


}