/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: LoginUserContext
 * Author: 25414
 * Date: 2019/5/11 17:23
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamautil.sessionutil;

import com.hg.gama.boot.config.filter.LoginFilter;
import com.hg.gama.gamaauth.util.AuthenticateToken;
import com.hg.gama.gamaauth.util.LoginUser;
import com.hg.gama.gamautil.numasutil.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class LoginUserContext {
    private static LoginUserContext instance;
    private HashMap<Integer, LoginUser> userMap;
    private static final Integer loginHoldTime=  SpringContextHolder .getInt("login.context.holdtime",120);

    private LoginUserContext() {
        userMap = new HashMap<Integer, LoginUser>();
    }

    public static LoginUserContext getContext() {
        if (instance == null) {
            instance = new LoginUserContext();
        }
        return instance;
    }

    public synchronized void addUser(LoginUser user) {
        if (user != null) {
            userMap.put(user.getUserId(), user);
        }
    }

    public synchronized void delUser(Integer id) {
        //刷新时间超时
        if( System.currentTimeMillis()-userMap.get(id).getRefreshTime()>(long)loginHoldTime*1000){
            userMap.remove(id);
        }
    }

    public void delUserTimeOut(){
        userMap.forEach((k,v)->{
            delUser(k);
        });
    }

    public  LoginUser getCurrentUser() {
        return userMap.get(SessionUtilGama.getSessionUserFromSession());
    }

    public  LoginUser checkLoginStatus(HttpServletRequest request){
        AuthenticateToken token= LoginFilter.getAccessToken(request);
        if(token==null)return null;
        LoginUser loginUser=userMap.get(token.getUserId());
        if(loginUser==null)return null;

        //刷新时间未超时
        if( System.currentTimeMillis()-loginUser.getRefreshTime()>(long)loginHoldTime*1000){
            return null;
        }
        //ip存在且等
        String ip=SessionUtilGama.getIpAddress(request);
        if(ip!=null&&!ip.equals(loginUser.getAdd()))return null;
        //更新刷新时间
        loginUser.setRefreshTime(System.currentTimeMillis());
        return loginUser;
    }




}