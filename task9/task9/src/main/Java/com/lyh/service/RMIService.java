package com.lyh.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;


public class RMIService {
    private static final Logger logger = LoggerFactory.getLogger(RMIService.class);

    public StudentService getStudentService() {
        StudentService studentService = null;
//        均匀分布的随机数
        int num = Math.random()>0.5?1:0;
        if (num == 0) {
            try {
                logger.info("调用studentClient的服务");
                studentService = (StudentService) Naming.lookup("rmi://127.0.0.1:1098/studentService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用studentClient的服务失败，开始调用studentClient2的服务");
                try {
                    studentService = (StudentService) Naming.lookup("rmi://127.0.0.1:1099/studentService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        } else {
            try {
                logger.info("调用studentClient2的服务");
                studentService = (StudentService) Naming.lookup("rmi://127.0.0.1:1099/studentService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用studentClient2的服务失败，开始调用studentClient的服务");
                try {
                    studentService = (StudentService) Naming.lookup("rmi://127.0.0.1:1098/studentService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        }
        logger.info(" studentService:{}", studentService);
        return studentService;
    }

    public JobService getJobService() {
        JobService jobService = null;
//        均匀分布的随机数
        int num = Math.random()>0.5?1:0;
        if (num == 0) {
            try {
                logger.info("调用jobClient的服务");
                jobService = (JobService) Naming.lookup("rmi://127.0.0.1:1098/jobService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用jobClient的服务失败，开始调用jobClient2的服务");
                try {
                    jobService = (JobService) Naming.lookup("rmi://127.0.0.1:1099/jobService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        } else {
            try {
                logger.info("调用jobClient2的服务");
                jobService = (JobService) Naming.lookup("rmi://127.0.0.1:1099/jobService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用jobClient2的服务失败，开始调用jobClient的服务");
                try {
                    jobService = (JobService) Naming.lookup("rmi://127.0.0.1:1098/jobService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        }
        logger.info("jobService:{}", jobService);
        return jobService;
    }

    public UserService getUserService() {
        UserService userService = null;
//        均匀分布的随机数
        int num = Math.random()>0.5?1:0;
        if (num == 0) {
            try {
                logger.info("调用userClient的服务");
                userService = (UserService) Naming.lookup("rmi://127.0.0.1:1098/userService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用userClient的服务失败，开始调用userClient2的服务");
                try {
                    userService = (UserService) Naming.lookup("rmi://127.0.0.1:1099/userService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        } else {
            try {
                logger.info("调用userClient2的服务");
                userService = (UserService) Naming.lookup("rmi://127.0.0.1:1099/userService");
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
                logger.info("调用userClient2的服务失败，开始调用jobClient的服务");
                try {
                    userService = (UserService) Naming.lookup("rmi://127.0.0.1:1098/userService");
                } catch (NotBoundException | MalformedURLException | RemoteException e1) {
                    logger.info("服务器1,2都挂了，{}", e1);
                }
            }
        }
        logger.info("userService:{}", userService);
        return userService;
    }
}
