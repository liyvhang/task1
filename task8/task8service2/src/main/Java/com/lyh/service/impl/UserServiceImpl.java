package com.lyh.service.impl;

import com.lyh.dao.UserDao;
import com.lyh.entity.COSBean;
import com.lyh.entity.Page;
import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.lyh.utils.*;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;

    @Autowired
    SMS sms;

    @Autowired
    private SendEmail sendEmail;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int addUser(User user) {
        int id = userDao.addUser(user);
        if (id > 0) {
            logger.info("注册成功，马上设置缓存");
            redisUtil.set("user" + id, user);
        } else {
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
        int num =userDao.updateUser(user);
        if (num>0){
            //如果更新成功就替换已存在的 key(键) 的 value(数据值)
            redisUtil.set("user" + user.getName(), user);
            redisUtil.set("user" + user.getId(), user);
        }
        return num;
    }

    @Override
    public User getUser(Long id) {
        User user;
        if (redisUtil.get("user" + id) == null) {
            logger.info("该数据缓存不存在，马上设置");
            user = userDao.getUser(id);
            redisUtil.set("user" + id, user, 60 * 60);

        } else {
            logger.info("该数据缓存已存在，已读取");
            user = (User) redisUtil.get("user" + id);
        }
        return user;
    }

    @Override
    public User byNamePassword(String name, String password) {
        return userDao.byNamePassword(name, password);
    }

    @Override
    public List<User> findUser(Page page) {
        logger.info("进入分页查询：当前页开始记录"+page.getStart());
        List<User> users;
        if (redisUtil.get("user"+page.getStart())==null){
            logger.info("该数据没有缓存，准备设置缓存");
            users = userDao.findUser(page);
            redisUtil.set("students"+page.getStart(),users);
        }else {
            logger.info("该数据已经有缓存，准备读取缓存");
            users = (List<User>)redisUtil.get("users"+page.getStart());
            logger.info("数据为：",users);
        }
        return users;
    }

    @Override
    public int total() {
        return userDao.total();
    }

    @Override
    public User findUserByName(String name) {
        User user;
        if (redisUtil.get("user" + name) == null) {
            logger.info("该数据缓存不存在，马上设置");
            user = userDao.findUserByName(name);
            redisUtil.set("user" + name, user, 60 * 60);

        } else {
            logger.info("该数据缓存已存在，已读取");
            user = (User) redisUtil.get("user" + name);
        }
        return user;
    }


    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public User findByPhone(String phone) {
        User user;
        if (redisUtil.get("userPhone" + phone) == null) {
            logger.info("该数据缓存不存在，马上设置");
            user = userDao.findByPhone(phone);
            redisUtil.set("userPhone" + phone, user, 60 * 60);

        } else {
            logger.info("该数据缓存已存在，已读取");
            user = (User) redisUtil.get("userPhone" + phone);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user;
        if (redisUtil.get("userEmail" + email) == null) {
            logger.info("该数据缓存不存在，马上设置");
            user = userDao.findByEmail(email);
            redisUtil.set("userEmail" + email, user, 60 * 60);

        } else {
            logger.info("该数据缓存已存在，已读取");
            user = (User) redisUtil.get("userEmail" + email);
        }
        return user;
    }

    @Override
    public int sendPhone(String phone) {
        //邮箱发送初始时间
        long phoneTime = 0;
        //邮箱发送累计次数
        int phoneFrequency = 0;
        if (null != redisUtil.get("phoneTime" + phone)) {
            phoneTime = (long) redisUtil.get("phoneTime" + phone);
            long data = System.currentTimeMillis();
            if ((data - phoneTime) < 1000 * 60) {
                return 2;
            }
        }
        if (null != redisUtil.get("phoneFrequency" + phone)) {
            phoneFrequency = (int) redisUtil.get("phoneFrequency" + phone);
            logger.info("获得:" + redisUtil.get("phoneFrequency" + phone));
            logger.info("获得：" + phoneFrequency);
            if (phoneFrequency >= 3) {
                return 3;
            }
        }
        //生成长度为6的数字
        String msgCode = CodeUtil.getRandNumberCode(6);
        logger.info("手机号验证码为：" + msgCode);
        String msgStatus = null;
        msgStatus = String.valueOf(sms.sendMessage(phone, msgCode));
        if (msgStatus != null && "true".equals(msgStatus)) {
            redisUtil.set("phoneTime" + phone, System.currentTimeMillis(), 60);
            redisUtil.set("phoneFrequency" + phone, ++phoneFrequency, 60 * 60 * 24);
            redisUtil.set("phoneMsgCode" + msgCode, msgCode, 60);
            logger.info("设置:" + redisUtil.get("phoneFrequency" + phone));
            return 0;
        }
        return 1;
    }

    @Override
    public int sendEmail(String email) {
        //邮箱发送初始时间
        long emailTime = 0;
        //邮箱发送累计次数
        int emailFrequency = 0;
        //判断验证码是否超过60秒
        if (null != redisUtil.get("emailTime" + email)) {
            emailTime = (long) redisUtil.get("emailTime" + email);
            long data = System.currentTimeMillis();
            if ((data - emailTime) < 1000 * 60) {
                return 2;
            }
        }
        //判断24小时之内发送验证码次数是否超过3次
        if (null != redisUtil.get("emailFrequency" + email)) {
            emailFrequency = (int) redisUtil.get("emailFrequency" + email);
            logger.info("获得:" + redisUtil.get("emailFrequency" + email));
            logger.info("获得：" + emailFrequency);
            if (emailFrequency >= 3) {
                return 3;
            }
        }
        //生成长度为6的数字
        String msgCode = CodeUtil.getRandNumberCode(6);
        logger.info("邮箱验证码为：" + msgCode);
        String msgStatus = null;
        try {
            //使用sendCloud发送邮箱验证码
            msgStatus = String.valueOf(sendEmail.send_common(email, msgCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断发送结果是否成功，成功则将验证码、当前时间以及发送次数放入缓存
        if (msgStatus != null && "true".equals(msgStatus)) {
            //发送邮箱验证码当前时间
            redisUtil.set("emailTime" + email, System.currentTimeMillis(), 60);
            //发送验证码的缓存
            redisUtil.set("emailMsgCode" + email, msgCode, 60);
            //发送验证码次数
            redisUtil.set("emailFrequency" + email, ++emailFrequency, 60 * 60 * 24);
            logger.info("设置：" + redisUtil.get(("emailFrequency" + email)));
            return 0;
        }
        return 1;
    }

//    @Override
//    public String updateImg(MultipartFile file, User user) {
//
//        if (file == null || file.getSize() <= 0) {
//            logger.info("头像不能为空");
//        }
//        COSUtil cosUtil = new COSUtil();
//        if (!file.isEmpty()) {
//            try {
//                String name = cosUtil.uploadFile2Cos(file);
//                //图片名称
//                logger.info("name:" + name);
//                //上传腾讯云
//                String imgUrl = cosUtil.getImgUrl(name);
//                //图片地址
//                logger.info("imgUrl" + imgUrl);
//                //数据库保存图片地址
//                String dbImgUrl = imgUrl.substring(0, imgUrl.indexOf("?"));
//                logger.info("dbImgUrl:" + dbImgUrl);
//                //设置图像地址
//                user.setImg(dbImgUrl);
//                //调用修改方法
//                int isModifyUser = userDao.updateUser(user);
//                if (isModifyUser == 1) {
//                    return "0";
//                } else {
//                    return "1";
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "2";
//            }
//        } else {
//            return "3";
//        }
//    }
}
