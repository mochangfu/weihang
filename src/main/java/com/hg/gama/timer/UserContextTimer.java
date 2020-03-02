/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: UserContextTimer
 * Author: 25414
 * Date: 2019/5/12 13:22
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.timer;

import com.hg.gama.gamautil.sessionutil.LoginUserContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@EnableScheduling
public class UserContextTimer {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//每隔10分执行一次
    @Scheduled(fixedDelay = 600000)
    public void userContextFreshTasks() {
        LoginUserContext.getContext().delUserTimeOut();
        System.out.println("每隔10分-用户登录状态刷新：");
    }


}
