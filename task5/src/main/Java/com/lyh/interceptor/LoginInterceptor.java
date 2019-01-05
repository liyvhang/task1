package com.lyh.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lyh.entity.User;
import com.lyh.service.UserService;
import com.lyh.utils.DESUtil;
import com.lyh.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        logger.info("请求的URL:"+url);
        //url:注册 ，登录页面是公开的，这个demo是除了该页面可以公开访问的，其他的都进行拦截
        if(url.indexOf("login.action")>=0){
            //login.action的.action与applicationContext.xml中的mapping path="/*.action"一致
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            // 遍历cookie如果找到登录状态则返回true执行原来controller的方法
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();

                    logger.info("token==="+token);
                    JwtUtils jwtUtils = new JwtUtils();
                    DecodedJWT jwt = jwtUtils.decodedToken(token);
                    System.out.println("jwt = " + jwt);
                    long date = System.currentTimeMillis();
                    String name =jwt.getClaim("username").asString();
                    logger.info("加密后的用户名"+name);
                    User user = userService.findUserByName(name);
                    System.out.println("user = " + user);
//                    检验token是否过期，签名是否一样，用户是否为空，用户名是否一致
                    if (date<jwt.getExpiresAt().getTime() && "jnshu".equals(jwt.getIssuer()) && user !=null && user.getName().equals(name)){
                        logger.info("\n");
                        logger.info("token有效");
//                        60分钟后过期
                        long maxAge =60L*60L*1000L;
                        String newToken = jwtUtils.getToken(jwt.getClaim("username").asString(),maxAge);
                        cookie.setValue(newToken);
                        cookie.setPath("/");
//                        1天到期
                        cookie.setMaxAge(24*60*60);
                        response.addCookie(cookie);
                        request.getSession().setAttribute("username",name);
                        return true;
                    }else {
                        logger.info("\n");
                        logger.info("token无效");
                        request.setAttribute("msg", "登录验证过期，请重新登录！");
                        cookie.setValue(null);
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        request.getSession().invalidate();
                        request.getRequestDispatcher("/login").forward(request,response);
                        return false;
                    }
                }
            }
        }
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//        //获取请求的URL
//        String url = request.getRequestURI();
//        logger.info("请求的URL:"+url);
//        //url:注册 ，登录页面是公开的，这个demo是除了该页面可以公开访问的，其他的都进行拦截
//        if(url.indexOf("login.action")>=0){
//            //login.action的.action与applicationContext.xml中的mapping path="/*.action"一致
//            return true;
//        }
//        //获取Session
//        HttpSession session = request.getSession();
//        String username = (String)session.getAttribute("name");
////        User user = (User)session.getAttribute("user");
//        //loginUser是登录后存到会话中的用户对象
//        logger.info("用户名："+username);
//        if(username != null){
//            return true;
//        }
//        //不符合条件的，跳转到登录界面
//        logger.info("没有登录不能访问此页面");
////        request.getRequestDispatcher("/WEB-INF/views/login").forward(request, response);
//        response.sendRedirect("/login");
//        return false;
//    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        Cookie[] cookies = request.getCookies();
//        logger.info("cookies"："+cookies);
//        String name = null;
//        String password = null;
//        if (cookies ==null||cookies.length == 0) {
//            logger.info("你没有cookie信息");
//        } else {
//            for (Cookie cookie : cookies) {
//                logger.info("cookie.getValue()里面有那些信息" + cookie.getValue());
//                if (cookie.getName().equals("name")) {
//                    name = cookie.getValue();
//                  request.getSession().setAttribute("name",name)
//                    logger.info("nameCookie的值value是" + name);
//                    if (JWT.parseJWT(name) != null)
//                        logger.info("验证成功");
//                    return true;
//                }
//            }
//        }
//        response.sendRedirect("/student");
//        return false;
//    }

}