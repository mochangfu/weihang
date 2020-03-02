package com.hg.gama.gamaauth.util;

/**
 * 错误码
 */
public class AuthCode {

    public static final int SUCCESS = 0;

    //异常
    public static final int FAIL_INNER = 1;

    //应用授权失败
    public static final int FAIL_AUTH_ILLEGAL = 2;

    //OAUTH -------------------------------------
    //access_token无效
    public static final int OAUTH_FAIL_ILLEGAL_ACCESSTOKEN = 1000;

    //鉴权失效，用户已经在其他设备登录
    public static final int OAUTH_FAIL_OTHER_LOGIN = 1001;

    //access_token过期
    public static final int OAUTH_FAIL_ACCESSTOKEN_EXPIRE = 1002;

    //LOGIN -------------------------------------
    //重复登录
    public static final int LOGIN_FAIL_REPEAT_LOGIN = 2000;

    //登录方式无效
    public static final int LOGIN_FAIL_TYPE_ILLEGAL = 2001;

    //登录账户类型无效
    public static final int LOGIN_FAIL_ACCOUNT_TYPE_ILLEGAL = 2001;

    //账户或密码错误
    public static final int LOGIN_FAIL_ACCOUNT_UNMATCH = 2002;

    //账户无效
    public static final int LOGIN_ACCOUNT_DISABLED = 2003;

    //登录请求参数错误
    public static final int LOGIN_REQUEST_PARAM_ERROR = 2004;

    //permisson--------------------------------------
    //权限未授权
    public static final int PERM_UNPERMITTED = 3000;

    //角色未授权
    public static final int ROLE_UNPERMITTED = 3001;
}
