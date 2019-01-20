package com.lyh.service.impl;

import com.lyh.dao.UserDao;
import com.lyh.entity.Page;
import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    MemCachedClient memCachedClient;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public int addUser(User user) {
       int id = userDao.addUser(user);
        if (id>0){
            memCachedClient.set("user"+id,user);
        }
        return id;
    }

    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User byNamePassword(String name, String password) {
        return userDao.byNamePassword(name,password);
    }

    @Override
    public List<User> findUser(Page page) {
        return userDao.findUser(page);
    }

    @Override
    public int total() {
        return userDao.total();
    }

    @Override
    public User findUserByName(String name) {
        User user = (User)memCachedClient.get("user"+name);
        if (user !=null){
            logger.info("该数据缓存已存在，已读取");
        }else {
            user = userDao.findUserByName(name);
            memCachedClient.set("user"+name,user);
        }
        return user;
    }


    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }
}
