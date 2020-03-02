/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: HgAuthenticate
 * Author: 25414
 * Date: 2019/4/7 17:10
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamaauth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hg.gama.gamashop.model.User;
import com.hg.gama.gamashop.service.UserService;
import com.hg.gama.gamautil.numasutil.SpringContextHolder;
import com.hg.gama.gamautil.numasutil.util.UUIDGen;
import com.hg.gama.gamautil.sessionutil.LoginUserContext;
import com.hg.gama.gamautil.sessionutil.SessionUtilGama;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class HgAuthenticate {
    private static final Logger logger = LoggerFactory.getLogger(HgAuthenticate.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String ACCESS_TOKEN = "accessToken";
    public static final Integer cookieMaxAge =SpringContextHolder.getInt("server.cookie.timeout",60);

    public  static LoginUser webLogin(HttpServletRequest request, HttpServletResponse response, AuthenticateToken token){
        //验证用户信息并创建session信息
        LoginUser sessionUser = authenticate(token);
        //记录访问ip
        String ip=SessionUtilGama.getIpAddress(request);
        sessionUser.setAdd(ip);
        if(sessionUser==null){
            throw new AuthException(AuthCode.LOGIN_FAIL_ACCOUNT_UNMATCH, "用户名密码错误");
        }
        //用户验证信息并存入会话
        LoginUserContext.getContext().addUser(sessionUser);
        //写入cookie
        String accessTokenStr = sessionUser.getAccessTokenStr();
        writeCookie(response, accessTokenStr);
        return sessionUser;
    }

    public static Integer loginOut(HttpServletRequest request, HttpServletResponse response){
        LoginUser sessionUser = LoginUserContext.getContext().getCurrentUser();
        if(sessionUser==null||sessionUser.getUser()==null){
            logger.error("未登录");
            return -1;
           // throw new AuthException(AuthCode.LOGIN_FAIL_ACCOUNT_UNMATCH, "未登录");
        }else {
            logger.info("web端账户登出，username:[" + sessionUser.getUser().getUsername() + "] ");
        }

        LoginUserContext.getContext().delUser(sessionUser.getUserId());
        clearCookie(response);
        return 1;
    }

    public  static void requestRedirect(HttpServletResponse response, String appuri) throws Exception{
        try {
            response.sendRedirect(appuri);
        } catch (IOException e) {
            logger.error("redirectIfRequired跳转失败, appuri:[" + appuri + "]");
            throw e;
        }
    }

    public  static void requestDispatcher(HttpServletRequest request, HttpServletResponse response, String appuri) throws Exception
    {
        try {
            request.getRequestDispatcher(appuri).forward(request, response);
        } catch (Exception e) {
            logger.error("requestDispatcher跳转失败, appuri:[" + appuri + "]");
            throw e;
        }
    }

    //验证用户信息并创建session信息
    public static LoginUser authenticate(AuthenticateToken token){

        UserService userService= SpringContextHolder.getBean("userService");
        User user =userService.getUserByUsername(token.getUsername());
        if(user==null||!checkPassword(token.getPassword(),user)){
            logger.error("用户名密码错误");
            return  null;
        }
        token.setUserId(user.getId());
        LoginUser sessionUser = new LoginUser(user.getId(),token,user, System.currentTimeMillis(), UUIDGen.UUID());
        sessionUser.setAccessTokenStr(getTokenStr(sessionUser.getAccessToken()));
        getAllUserInfo(sessionUser);

        return  sessionUser;
    }





    //密码校验
    public static Boolean checkPassword(String password, User user){
        if(password==null||user.getPasswd()==null){
            return false;
        }
        return password.equals(user.getPasswd());
    }

    //填充用户权限等其他信息
    public static void  getAllUserInfo(LoginUser sessionUser){
    }

    private static String getTokenStr(AuthenticateToken accessToken) {
        try {
            accessToken.setPassword(null);
            String tokenStr = DatatypeConverter.printBase64Binary(OBJECT_MAPPER.writeValueAsBytes(accessToken));
            return tokenStr;
        } catch (JsonProcessingException e) {
         logger.error("序列号失败");
         return "error";
        }
    }

    public static void writeCookie(HttpServletResponse response, String accessToken) {
        Cookie tokenCookie = new Cookie(ACCESS_TOKEN, accessToken);
        // tokenCookie.setMaxAge(Integer.MAX_VALUE); //不失效，是否失效仅依赖redis缓存是否存在
        //不设置maxAge,cookie随浏览器关闭失效
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setMaxAge(cookieMaxAge);
        response.addCookie(tokenCookie);
    }

    private static void clearCookie(HttpServletResponse response) {
        Cookie tokenCookie = new Cookie(ACCESS_TOKEN, null);
        tokenCookie.setMaxAge(0);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
    }




}
