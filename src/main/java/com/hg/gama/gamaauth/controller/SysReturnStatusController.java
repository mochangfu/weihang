/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: SysReturnStatusController
 * Author: 25414
 * Date: 2019/5/1 14:49
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamaauth.controller;

import com.hg.gama.boot.sys.model.Result;
import com.hg.gama.boot.sys.model.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = {"系统级返回页面"})
@RestController
@RequestMapping("/sys")
public class SysReturnStatusController {

    @ApiOperation("未登录请求跳转地址")
    @GetMapping("/filterException")
    public Result filterException(HttpServletRequest request, HttpServletResponse response) {
        return new Result(Status.LOGIN, "未登录或登录超时");
    }
    @ApiOperation("未登录请求跳转地址")
    @PostMapping("/filterException")
    public Result filterExceptionPost(HttpServletRequest request, HttpServletResponse response) {
        return new Result(Status.LOGIN, "未登录或登录超时");
    }


}
