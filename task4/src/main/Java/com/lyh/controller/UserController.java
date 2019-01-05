package com.lyh.controller;

import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.lyh.utils.JwtUtils;
import com.lyh.utils.MD5utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Model model,Map map,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        logger.info("进入登录界面");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        logger.info("user结果："+user);
        String password= null;

        if (user.getName()==null||user.getPassword()==null){
            logger.info("用户名和密码不能为空");
            return "login";
        }
//        user.setName(MD5Util.MD5(user.getName()));
//        logger.info(user.getName());
        logger.info(user.getPassword());

        String name = user.getName();
        logger.info("name==="+name);
        System.out.println("userService==============" + userService);
        User user1 = userService.findUserByName(name);
        if (user1 ==null){
            logger.info("用户名不存在");
            return "login";
        }
        password = user1.getPassword();

        //验证登录输入的密码与数据库保存的密码是否一致
        if (MD5utils.getSaltverifyMD5(user.getPassword(),password)){
            logger.info("登录成功");
            JwtUtils jwtUtils = new JwtUtils();
//            设置token过期时间1个小时
            long maxAge = 60L*60L* 1000L;
            String token = jwtUtils.getToken(name,maxAge);
            Cookie tokenCookie = new Cookie("token",token);
            logger.info("token="+token);
//            设置cookie过期时间1个小时
            tokenCookie.setMaxAge(24*60*60);
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
            HttpSession session = request.getSession();
            session.setAttribute("name",user.getName());
            logger.info("登陆成功，进入主页");
            String name1 =  user.getName();
            model.addAttribute("name",name1);
        return "redirect:/home";
        } else {
            map.put("code", "100");
            map.put("message", "登陆失败");
            map.put("data", "无法找到用户信息");
           return "login";
        }
    }
    //注册
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(Model model, User user,HttpServletRequest request, HttpServletResponse response){

        String message;
        String userName = user.getName();
        String password = user.getPassword();

        if (userName.isEmpty()||password.isEmpty()){
            message = "用户密码不能为空";
            model.addAttribute("items",message);
            logger.info("用户名和密码不能为空");
            return "register";
        }

        if (userService.findUserByName(userName) == null){
            logger.info("无该用户可以注册");
            //加密
//            user.setName(MD5Util.generate(user.getName()));
//            user.setPassword(MD5Util.generate(user.getPassword()));
            user.setPassword(MD5utils.getSaltMD5(user.getPassword()));
            userService.addUser(user);
            model.addAttribute("user",user);
            return "redirect:/login";
        }else {
            logger.info("已有该用户，无法注册");
            return "register";
        }
    }
//    注销

    @RequestMapping(value = "/loginOut",method = {RequestMethod.GET})
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies.length == 0){
            logger.info("没有cookie，无需注销");
        }else {
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
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
}
   