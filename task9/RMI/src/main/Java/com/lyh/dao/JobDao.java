package com.lyh.dao;

import com.lyh.entity.Job;

import java.util.List;

public interface JobDao {
    List<Job> findDevelopmentDirection(String developmentDirection);

    List<Job> selectJob();

    Job getId(Long id);

    int addJob(Job job);

    int deleteJob(Long id);

    int updateJobNum(String professionName);
}
