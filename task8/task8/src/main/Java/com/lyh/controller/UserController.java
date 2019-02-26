package com.lyh.controller;

import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.lyh.utils.*;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SMS sms;
    RedisUtil redisUtil;
    SendEmail sendEmail;

//    UserService userService;

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    UserService userService = (UserService)applicationContext.getBean("userRMIClient");

    @RequestMapping(value = "/login", method = GET)
    public String login() {
        System.out.println("进入登陆界面");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        logger.info("开始登录");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        String phone = user.getPhone();
        System.out.println("获得前端传递的电话号码" + phone);
        String email = user.getEmail();
        System.out.println("获得前端传递的邮箱号码" + email);
        logger.info("用户信息：" + user);
        logger.info("用户密码：" + user.getPassword());

        //判断是邮箱还是手机号的正则表达式
        //邮箱正则表达式
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        //手机号码正则表达式
        String ph = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        User userName = null;
        //判断输入的格式符合邮箱正则表达式就使用邮箱登录
        if (name.matches(em)) {
            logger.info("邮箱登录：" + name);
            userName = userService.findByEmail(name);
            logger.info("userName:" + userName);
            if (userName == null) {
                model.addAttribute("error", "邮箱或密码错误");
                return "login";
            }
        } else
            //判断输入的格式符合手机号正则表达式就使用手机号登录
            if (name.matches(ph)) {
                logger.info("手机号登录：" + name);
                userName = userService.findByPhone(name);
                logger.info("用户信息" + userName);
                if (userName == null) {
                    model.addAttribute("error", "手机号或密码错误");
                    return "login";
                }
            } else
                //判断输入的格式不符合手机和邮箱正则表达式就使用名字登录
                if (name != null) {
                    logger.info("用户名登录：" + name);
                    userName = userService.findUserByName(name);
                    logger.info("用户信息" + userName);
                    if (userName == null) {
                        model.addAttribute("error", "用户名或密码错误");
                        return "login";
                    }
                }
        //获得密码
        String password = null;
        password = userName.getPassword();
        //验证登录输入的密码与数据库保存的密码是否一致
        if (MD5utils.getSaltverifyMD5(user.getPassword(), password)) {
            logger.info("登录成功");
            JwtUtils jwtUtils = new JwtUtils();
            //设置token过期时间1个小时
            long maxAge = 60L * 60L * 1000L;
            //获得登录用户名
            String name1 = userName.getName();
            //获得用户的图片链接
            String img = userName.getImg();
            //使用jwt工具类加密token
            String token = jwtUtils.getToken(name1, maxAge);
            //把token放入cookie里
            Cookie tokenCookie = new Cookie("token", token);
            logger.info("token=" + token);
//            设置cookie过期时间1个小时
            tokenCookie.setMaxAge(24 * 60 * 60);
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
            HttpSession session = request.getSession();
            session.setAttribute("name", name1);
            session.setAttribute("img", img);
            logger.info("登陆成功，进入主页");
            //进入首页
            return "redirect:/home";
        } else {
            logger.info("登录失败，请重新登录");
            model.addAttribute("error2", "登录失败，请重新登录");
            return "login";
        }
    }

    // 邮箱注册
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String email() {
        return "email";
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public String email(Model model, User user, String msgCode) {
        logger.info("邮箱注册添加数据传参:" + user);
        logger.info("邮箱验证码为：" + msgCode);
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String userName = user.getName();
        String password = user.getPassword();
        String email = user.getEmail();
        //条件：用户名不能为空，密码不能为空，邮箱不能为空，邮箱格式不符合不能注册
        if (userName.isEmpty() || password.isEmpty() || email.isEmpty() || !email.matches(em)) {
            String message = "用户密码不能为空";
            model.addAttribute("items1", message);
            logger.info("用户名,邮箱和密码不能为空");
            return "email";
        }
        //条件：根据注册名查找信息为空，根据注册邮箱查找信息为空，从前端传递的邮箱验证码和redis缓存的验证码相同可以注册
        if (userService.findUserByName(userName) == null && userService.findByEmail(email) == null && msgCode.equals(redisUtil.get("emailMsgCode" + email))) {
            logger.info("无该用户可以注册");
            //加密
            user.setPassword(MD5utils.getSaltMD5(user.getPassword()));
            user.setImg("https://liyuhang-1257785836.cos.ap-beijing.myqcloud.com/1550233737593_.png");
            userService.addUser(user);
            model.addAttribute("emailMsgCode", msgCode);
            model.addAttribute("user", user);
            return "redirect:/login";
        } else {
            logger.info("已有该用户，无法注册");
            return "email";
        }
    }

    //电话注册
    @RequestMapping(value = "/phone", method = GET)
    public String phone() {
        logger.info("进入手机注册页面");
        return "phone";
    }

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public String phone(Model model, User user, String msgCode) {

        logger.info("手机号注册添加数据:" + user);
        logger.info("手机验证码为：" + msgCode);
        //手机号正则表达式
        String ph = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        String userName = user.getName();
        String password = user.getPassword();
        String phone = user.getPhone();
        //条件：用户名不能为空，密码不能为空，手机号不能为空，邮箱格式不符合不能注册
        if (userName.isEmpty() || password.isEmpty() || phone.isEmpty() || !phone.matches(ph)) {
            String message = "用户密码不能为空";
            model.addAttribute("items", message);
            logger.info("用户名,手机号和密码不能为空");
            return "phone";
        }
        //条件：根据注册名查找信息为空，根据注册手机号查找信息为空，从前端传递的电话验证码和redis缓存的验证码相同可以注册
        if (userService.findUserByName(userName) == null && userService.findByPhone(phone) == null && msgCode.equals(redisUtil.get("phoneMsgCode" + msgCode))) {
            logger.info("无该用户可以注册");
            //加密
            user.setPassword(MD5utils.getSaltMD5(user.getPassword()));
            //注册账号
            user.setImg("https://liyuhang-1257785836.cos.ap-beijing.myqcloud.com/1550233737593_.png");
            userService.addUser(user);
            model.addAttribute("phoneMsgCode", msgCode);
            model.addAttribute("users", user);
            //成功后返回登录界面登录
            return "redirect:/login";
        } else {
            logger.info("已有该用户，无法注册");
            model.addAttribute("msgCode", msgCode);
            model.addAttribute("user", user);
            return "phone";
        }
    }


    //    注销用户
    @RequestMapping(value = "/loginOut", method = GET)
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies.length == 0) {
            logger.info("没有cookie，无需注销");
        } else {
            //开始遍历cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    logger.info("开始清理");
                    cookie.setValue(null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
//                    移除对象
                    request.getSession().removeAttribute("name");
                }
            }
        }
        return "login";
    }

    //发送手机验证码
    @ResponseBody
    @RequestMapping(value = "phone/msgCode", method = GET)
    public Map phoneMsgCode(Model model, @RequestParam("phone") String phone) {
        logger.info("开始发送验证码");
        Map map = new HashMap();

        System.out.println("注册电话号码：" + phone);
        model.addAttribute("phone", phone);
        //手机号正则表达式
        String ph = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        if (phone.matches(ph) && userService.findByPhone(phone) == null) {
            int status = userService.sendPhone(phone);
            logger.info("发送状态码：" + status);
            if (status == 0) {
                map.put("status", 1);
                map.put("message", "验证码发送成功");
            }
            if (status == 1) {
                map.put("status", 5);
                map.put("message", "请稍后再试");
            }
            if (status == 2) {
                map.put("status", 6);
                map.put("message", "发送次数过于频繁");
            }
            if (status == 3) {
                map.put("status", 7);
                map.put("message", "发送验证码次数已经超过3次,24小时候重试");
            }
        } else {
            map.put("status", 2);
            map.put("message", "此手机已注册或者格式错误，请重新输入");
            logger.info("手机填写错误，请重写");
        }
        return map;
    }

    //发送邮箱验证码
    @ResponseBody
    @RequestMapping(value = "email/msgCode", method = GET)
    public Map EmailMsgCode(Model model, @RequestParam("email") String email) throws IOException {
        Map map = new HashMap();
        logger.info("开始发送验证码");
        System.out.println("注册邮箱为： " + email);
        model.addAttribute("phone", email);
        //邮箱号正则表达式
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if (email.matches(em) && userService.findByEmail(email) == null) {
            int status = userService.sendEmail(email);
            logger.info("发送状态码：" + status);
            if (status == 0) {
                map.put("status", 1);
                map.put("message", "验证码发送成功");
            }
            if (status == 1) {
                map.put("status", 4);
                map.put("message", "请稍后再试");
            }
            if (status == 2) {
                map.put("status", 5);
                map.put("message", "发送次数过于频繁");
            }
            if (status == 3) {
                map.put("status", 6);
                map.put("message", "发送验证码次数已经超过3次,24小时候重试");
            }
        } else {
            map.put("status", 2);
            map.put("message", "此邮箱号已注册或者格式错误，请重新输入");
            logger.info("邮箱号填写错误，请重写");
        }
        return map;
    }

    //上传文件以及更新用户信息
    @RequestMapping(value = "u/user/updateImg/{name}", method = POST)
    @ResponseBody
    public String updateImg(HttpSession session, MultipartFile multipartFile, @PathVariable String name) throws Exception {
        System.out.println("multipartFile = " + multipartFile);
        COSUtil cosUtil = new COSUtil();
        String fileName = cosUtil.uploadFile2Cos(multipartFile);
        //图片名称
        logger.info("fileName:" + fileName);
        //上传腾讯云
        String imgUrl = cosUtil.getImgUrl(fileName);
        //图片地址
        logger.info("图片地址为：" + imgUrl);
        //数据库保存图片地址,去除签名
        String dbImgUrl = imgUrl.substring(0, imgUrl.indexOf("?"));
        logger.info("图片去除签名地址为:" + dbImgUrl);
        User user = userService.findUserByName(name);
        System.out.println("根据用户名查询： " + user);
        user.setImg(dbImgUrl);
        if (dbImgUrl != null) {
            userService.updateUser(user);
            // 更新session值
            session.setAttribute("img", user.getImg());
        }
        return dbImgUrl;
    }

    //个人主页，根据用户名查找
    @RequestMapping(value = "/u/user/{name}")
    public ModelAndView userPage(@PathVariable(value = "name") String name, HttpServletRequest request) {
        logger.info("进入个人主页");
        ModelAndView mav = new ModelAndView("editUser");
        //开始根据用户名查找用户信息
        User user = userService.findUserByName(name);
        logger.info("获取个人信息：" + user);
        mav.addObject("user", user);
        mav.addObject("img", user.getImg());
        return mav;
    }
}
