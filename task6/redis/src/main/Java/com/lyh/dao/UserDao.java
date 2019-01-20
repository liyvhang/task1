package com.lyh.dao;

import com.lyh.entity.Page;
import com.lyh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //    添加
    int addUser(User user);
    //删除
    int deleteUser(Long id);
    //修改
    int updateUser(User user);
    //根据id查询
    User getUser(Long id);
    //用户登陆
    User byNamePassword(@Param("name")String name, @Param("password")String password);
    //查询
    List<User> findUser(Page page);

    int total();
    //用户名查询
    User findUserByName(String name);

    //全表查询
    List<User> listUser();
}
