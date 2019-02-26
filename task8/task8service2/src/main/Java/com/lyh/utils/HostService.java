package com.lyh.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HostService {
    public static void main(String[] args) {
//        System.setProperty("java.rmi.server.hostname","192.168.0.102");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        System.out.println("RMI服务伴随Spring的启动而启动了.....");
    }
}
