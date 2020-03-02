package com.hg.gama.gamautil.numasutil.constants;


import com.hg.gama.gamautil.numasutil.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class PropertyConstants {

    public static boolean isLocalDeploy() {
        String local = SpringContextHolder.getProperty("deploy.local");
        if (local == null) {
            return true;
        }
        return Boolean.valueOf(local);
    }

    public static Set<String> getAuthAppSecrets() {
        String authApps = SpringContextHolder.getProperty("auth.app");
        Set<String> authAppSecrets = new HashSet<>();
        if (StringUtils.isBlank(authApps)) {
            return authAppSecrets;
        }
        String[] authAppsPairs = authApps.split(",");
        for (String pair : authAppsPairs) {
            String[] namesecret = pair.trim().split(":");
            String secret = namesecret[1].trim();
            authAppSecrets.add(secret);
        }
        return authAppSecrets;
    }

    /**
     * 是否允许重复登录
     */
    public static boolean allowRepeatLogin() {
        String repeat = SpringContextHolder.getProperty("login.repeat");
        if (repeat == null) {
            return true;
        }
        return Boolean.valueOf(repeat);
    }

    public static boolean isDevMode() {
        String devMode = SpringContextHolder.getProperty("dev.mode");
        if (devMode == null) {
            return false;
        }
        return Boolean.valueOf(devMode);
    }

    public static long getRequestLimit() {
        String limit = SpringContextHolder.getProperty("req.limit");
        if (limit == null) {
            return 2500;//2500毫秒
        }
        return Long.valueOf(limit);
    }

    public static boolean isNewVacation() {
        String res = SpringContextHolder.getProperty("duty.vacation.workflow");
        if (res == null) {
            return false;
        }
        return Boolean.valueOf(res.trim());
    }

}
