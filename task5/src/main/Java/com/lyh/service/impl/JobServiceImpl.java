package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.entity.Job;
import com.lyh.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao dao;
    @Override
    public List<Job> findDevelopmentDirection(String developmentDirection) {
        return dao.findDevelopmentDirection(developmentDirection);
    }

    @Override
    public List<Job> selectJob() {
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
