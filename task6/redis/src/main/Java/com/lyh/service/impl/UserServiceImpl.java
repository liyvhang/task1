package com.lyh.service.impl;

import com.lyh.dao.UserDao;
import com.lyh.entity.Page;
import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.lyh.utils.RedisUtil;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public int addUser(User user) {
        int id = userDao.addUser(user);
        if (id>0){
            logger.info("注册成功，马上设置缓存");
            redisUtil.set("user"+id,user);
        }else {
            logger.info("注册失败，无法设置缓存");
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
        User user;
        if (redisUtil.get("user"+name) ==null){
            logger.info("该数据缓存不存在，马上设置");
            user = userDao.findUserByName(name);
            redisUtil.set("user"+name,user,60*60);

        }else {
            logger.info("该数据缓存已存在，已读取");
            user =(User) redisUtil.get("user"+name);
        }
        return user;
    }


    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }
}
