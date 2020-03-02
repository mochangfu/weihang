package com.hg.gama.gamaauth.controller;

import com.hg.gama.boot.sys.model.Result;
import com.hg.gama.boot.sys.model.Status;
import com.hg.gama.gamaauth.util.HgAuthenticate;
import com.hg.gama.gamaauth.util.LoginEntry;
import com.hg.gama.gamaauth.util.LoginUser;
import com.hg.gama.gamaauth.util.AuthenticateToken;
import com.hg.gama.gamashop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = {"登录注销接口"})
@RestController
@RequestMapping
public class WebController {

    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @ApiOperation("登录get")
    @GetMapping("/login/account")
    public Result loginAccount(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "deviceId", required = false) String deviceId,
                               @RequestParam(value = "appuri", required = false) String appuri,
                               @RequestParam(value = "entryType", required = false) LoginEntry entryType) {

        AuthenticateToken token;
        if(entryType==null)entryType = LoginEntry.UNKNOWN;
        token=new AuthenticateToken(username,password,deviceId,entryType,System.currentTimeMillis());
        logger.info("web端账户登录" );
        try {
            LoginUser sessionUser =HgAuthenticate.webLogin(request, response, token);
            logger.info("登录成功："+sessionUser.getUser().getUsername()+ ":"+sessionUser.getAdd());
            return new Result(sessionUser);
        }catch (Exception e1) {
            logger.info("用户名密码错误");
            e1.printStackTrace();
            return new Result(Status.FAIL,"用户名密码错误");
        }
    }
    @ApiOperation("登录post")
    @PostMapping("/login/account")
    public Result loginWeb(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody AuthenticateToken token) {
        if(token.getLoginEntry()==null)token.setLoginEntry(LoginEntry.UNKNOWN);
        logger.info("web端账户登录" );
        try {
            LoginUser sessionUser =HgAuthenticate.webLogin(request, response, token);
            logger.info("登录成功："+sessionUser.getUser().getUsername() );
            return new Result(sessionUser);
        }catch (Exception e1) {
            logger.info("用户名密码错误");
            e1.printStackTrace();
            return new Result(Status.FAIL,"用户名密码错误");
        }
    }

    @ApiOperation("注销")
    @GetMapping("/logout")
    public Result logoutAccount(HttpServletRequest request, HttpServletResponse response
                            ) {

        try {
            Integer logoutsStatus = HgAuthenticate.loginOut(request, response);
            if (logoutsStatus == 1) {
                return new Result(Status.SUCCESS, "退出成功");
            }
            return new Result(Status.FAIL, "未登录或登录超时");
        } catch (Exception e1) {
            e1.printStackTrace();
            return new Result(Status.FAIL, "未登录或登录超时");
        }
    }

}
// @RequestBody (required = false)AuthenticateToken token,//完整的body参数
//@JsonParam(value = "token") AuthenticateToken token//指定名称的body参数