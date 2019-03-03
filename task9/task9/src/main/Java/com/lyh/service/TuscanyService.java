package com.lyh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class TuscanyService {
    /*private static final Logger logger = LoggerFactory.getLogger(TuscanyService.class);
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");

    public StudentService getStudentService() {
        Random random = new Random();
        int num = random.nextInt(2);
        System.out.println("num = " + num);
        if (num == 1) {
            try {
                logger.info("调用studentClient的服务");
                return (StudentService) applicationContext.getBean("studentClient");
            } catch (Exception b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用studentClient的服务失败，开始调用studentClient2的服务");
                return (StudentService) applicationContext.getBean("studentClient2");
            }
        } else {
            try {
                logger.info("调用studentClient2的服务");
                return (StudentService) applicationContext.getBean("studentClient2");
            } catch (Exception b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用studentClient2的服务失败，开始调用studentClient的服务");
                return (StudentService) applicationContext.getBean("studentClient");
            }
        }
    }

    public JobService getJobService() {
        Random random = new Random();
        int num = random.nextInt(2);
        if (num == 1) {
            try {
                logger.info("调用jobClient的服务");
                return (JobService) applicationContext.getBean("jobClient");
            } catch (BeanCreationException b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用studentClient2的服务失败，开始调用studentClient的服务");
                return (JobService) applicationContext.getBean("jobClient2");
            }
        } else {
            try {
                logger.info("调用jobClient2的服务");
                return (JobService) applicationContext.getBean("jobClient2");
            } catch (BeanCreationException b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用jobClient2的服务失败，开始调用jobClient的服务");
                return (JobService) applicationContext.getBean("jobClient");
            }
        }
    }

    public UserService getUserService() {
        Random random = new Random();
        int num = random.nextInt(2);
        if (num == 1) {
            try {
                logger.info("调用userClient的服务");
                return (UserService) applicationContext.getBean("userClient");
            } catch (BeanCreationException b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用userClient的服务失败，开始调用userClient2的服务");
                return (UserService) applicationContext.getBean("userClient2");
            }
        } else {
            try {
                logger.info("调用userClient2的服务");
                return (UserService) applicationContext.getBean("userClient2");
            } catch (BeanCreationException b) {
                logger.info(b.getLocalizedMessage());
                logger.info("调用userClient2的服务失败，开始调用userClient的服务");
                return (UserService) applicationContext.getBean("userClient");
            }
        }

    }*/
}
