package com.hg.gama.gamaauth.util;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hg.gama.gamashop.model.User;

import java.io.Serializable;
import java.util.HashMap;

public class LoginUser implements Serializable {
    private static final long serialVersionUID = -2132616335093395790L;
    private  Integer userId;
    @JsonIgnore
    private AuthenticateToken accessToken;
    private String accessTokenStr;
    private User user;
    private long refreshTime;
    private String add;
    //唯一标识
    private String uuid;
    //其他信息
    private HashMap<String, Object> infoMap;

    public LoginUser() {

    }
    public LoginUser(Integer userId, AuthenticateToken accessToken, User user, long refreshTime, String uuid) {
        this.userId=userId;
        this.accessToken =  accessToken;
        this.user = user;
       this.refreshTime = refreshTime;
        this.uuid=uuid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AuthenticateToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AuthenticateToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenStr() {
        return accessTokenStr;
    }

    public void setAccessTokenStr(String accessTokenStr) {
        this.accessTokenStr = accessTokenStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public HashMap<String, Object> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(HashMap<String, Object> infoMap) {
        this.infoMap = infoMap;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

}


