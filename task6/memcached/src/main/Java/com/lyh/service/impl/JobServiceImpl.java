package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.entity.Job;
import com.lyh.service.JobService;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao dao;
    @Autowired
    MemCachedClient memCachedClient;
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Override
    public List<Job> findDevelopmentDirection(String developmentDirection) {
        return dao.findDevelopmentDirection(developmentDirection);
    }

    @Override
    public List<Job> selectJob() {
        List<Job> jobs;
        if (memCachedClient.get("jobs") == null){
            logger.info("职业类表没有缓存，开始设置缓存 ");
            jobs = dao.selectJob();
           memCachedClient.set("jobs",jobs);
        }else {
            logger.info("职业类表已有缓存，开始读取缓存 ");
            jobs = (List<Job>)memCachedClient.get("jobs");
            logger.info("职业类表数据为："+jobs);
        }
        return dao.selectJob();
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
