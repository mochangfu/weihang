package com.hg.gama.gamaauth.util;

import org.springframework.lang.NonNull;

public class AuthenticateToken {
    @NonNull
    private  Integer userId;
    private String username;
    private String password;
    private String deviceId;
    private  LoginEntry loginEntry;
    private long refreshTime;
    private long lastRefreshTime;


    public AuthenticateToken() {
    }
    public AuthenticateToken(String username, String password, String deviceId, LoginEntry loginEntry, long refreshTime) {
    this.username=username;
    this.password=password;
    this.deviceId=deviceId;
    this.loginEntry=loginEntry;
    this.refreshTime=refreshTime;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LoginEntry getLoginEntry() {
        return loginEntry;
    }

    public void setLoginEntry(LoginEntry loginEntry) {
        this.loginEntry = loginEntry;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }


    @Override
    public String toString() {
        return "AuthenticateToken{" +
                "userId='" + userId + '\'' +
                ",username='" + username + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", loginEntry=" + loginEntry +
                ", refreshTime=" + refreshTime +
                ", lastRefreshTime=" + lastRefreshTime +
                '}';
    }
}
