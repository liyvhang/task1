package com.lyh.utils;

import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;

public class HostService {
    public static void main(String[] args) {
        //System.setProperty("java.rmi.server.hostname","192.168.0.102");
        Node node = NodeFactory.newInstance().createNode("service.composite");
        node.start();
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        //System.out.println("RMI服务伴随Spring的启动而启动了.....");
        System.out.println("服务端启动");
        // node.stop();
    }
}
