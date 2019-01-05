package com.lyh.service;

import com.lyh.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> findDevelopmentDirection(String developmentDirection);

    List<Job> selectJob();

    Job getId(Long id);

    int addJob(Job job);

    int deleteJob(Long id);

    int updateJob(Job job);

    /**
     * @param professionName ss
     * @return
     */
    int updateJobNum(String professionName);
}
