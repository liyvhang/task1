package com.lyh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softamis.cluster4spring.rmi.RmiProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class RMIService {
    private static final Logger logger = LoggerFactory.getLogger(RMIService.class);

    @Autowired
    @Qualifier("studentRMIClient")
    private RmiProxyFactoryBean rmiProxyFactoryStudent;

    @Autowired
    @Qualifier("jobRMIClient")
    private RmiProxyFactoryBean rmiProxyFactoryJob;

    @Autowired
    @Qualifier("userRMIClient")
    private RmiProxyFactoryBean rmiProxyFactoryUser;

    public StudentService getStudentService(){
        StudentService studentService;
        studentService =(StudentService) rmiProxyFactoryStudent.getObject();
        return studentService;
    }

    public JobService getJobService(){
        JobService jobService;
        jobService =(JobService) rmiProxyFactoryStudent.getObject();
        return jobService;
    }

    public UserService getUserService(){
        UserService userService;
        userService =(UserService) rmiProxyFactoryStudent.getObject();
        return userService;
    }
}
