package com.lyh.service;

import com.lyh.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //    删除
    int deleteByPrimaryKey(Long id);
    //添加
    int insert(User record);
    //动态添加
    int insertSelective(User record);
    //根据id删除
    User selectByPrimaryKey(Long id);
    //动态更新
    int updateByPrimaryKeySelective(User record);
    //更新
    int updateByPrimaryKey(User record);
    //条件查询
    List<User> findUser(@Param("userName")String userName, @Param("role")String role);

}
