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

import com.hg.gama.gamaauth.util.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisUtilGama {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtilGama.class);

    public static LoginUser getSessionUserFromRedis() {
        LoginUser sessionUser= new LoginUser();
        return sessionUser;
    }

    public static Object putUserIntoRedis(LoginUser user) {

        return user;

    }
    public static Object removeUserFromSession( ) {
        Object user = null;
        return user;
    }

}