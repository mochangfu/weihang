/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: SessionUtilGama
 * Author: 25414
 * Date: 2019/4/7 16:53
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamautil.sessionutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtilGama {
    private static final Logger logger = LoggerFactory.getLogger(SessionUtilGama.class);
    // 获取一个session对象

    /**
     * user在session中的名字，也就是键值对的键。
     */
    private static final String USER_IN_SESSION = "user";

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (HttpServletRequest) attrs.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (HttpServletResponse) attrs.getResponse();
    }

    /**
     * 从session中获取到当前用户
     *
     * @return
     */
    public static Integer getSessionUserFromSession() {
        Integer sessionUser=(Integer) getSession().getAttribute(USER_IN_SESSION);
        return sessionUser;
    }

    /**
     * 将user放入Session对象中

     */
    public static Object putUserIntoSession(Integer userId) {
        getSession().setAttribute(USER_IN_SESSION, userId);
        return getSessionUserFromSession();
    }


    /**
     * 从session中移除User对象
     *
     * @return
     */
    public static Object removeUserFromSession( ) {
        Object user = getSessionUserFromSession();
        getSession().removeAttribute(USER_IN_SESSION);
        return user;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100

     * 用户真实IP为：
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}