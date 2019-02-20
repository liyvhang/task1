package com.lyh.service;

import com.lyh.entity.Page;
import com.lyh.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    //    添加
    int addUser(User user);

    //删除
    int deleteUser(Long id);

    //修改
    int updateUser(User user);

    //根据id查询
    User getUser(Long id);

    //用户登陆
    User byNamePassword(@Param("name") String name, @Param("password") String password);

    //查询
    List<User> findUser(Page page);

    //总条数
    int total();

    //用户名查询
    User findUserByName(String name);

    //全表查询
    List<User> listUser();

    //根据电话查询
    User findByPhone(String phone);

    //根据邮箱号查询
    User findByEmail(String email);

    //发送手机号
    int sendPhone(String phone);

    //发送邮箱号
   int sendEmail(String email);

//   //图片上传
//    String updateImg(MultipartFile file, User user);
}