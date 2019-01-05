package com.lyh.service.impl;

import com.lyh.dao.UserDao;
import com.lyh.entity.Page;
import com.lyh.entity.User;
import com.lyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
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
        return userDao.findUserByName(name);
    }


    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }
}
