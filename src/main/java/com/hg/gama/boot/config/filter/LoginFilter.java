package com.hg.gama.boot.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hg.gama.gamaauth.util.*;
import com.hg.gama.gamautil.numasutil.exception.GamaException;
import com.hg.gama.gamautil.sessionutil.LoginUserContext;
import com.hg.gama.gamautil.sessionutil.SessionUtilGama;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class LoginFilter extends BaseFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    //nursecare登录授权secret
    private static final String AUTH_SECRET = "Awd13Fv8gL";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException {

        try {
            System.out.println("filter:"+request.getServletPath());
            //AuthenticateToken token= getAccessToken(request);
            LoginUser sessionUser=LoginUserContext.getContext().checkLoginStatus(request);
            //web访问为长连接、浏览器未关闭则有固定会话,用单例map
            if(sessionUser==null)throw new GamaException("未登录");
            System.out.println(":"+sessionUser.getAdd());
            SessionUtilGama.putUserIntoSession(sessionUser.getUserId());

            //HgAuthenticate.writeCookie(response,sessionUser.getAccessTokenStr());

            super.doFilter(request, response, chain);
        } catch (Exception e3) {
            e3.printStackTrace();
            try {
                HgAuthenticate.requestDispatcher(request, response, "/sys/filterException");
            }catch (Exception e){
                logger.info("/gama/filterException地址失效？");
            }
        } finally {
        }

    }

    private void closeErrorResponse(HttpServletResponse response, String errMsg) throws IOException {
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.getOutputStream().write(errMsg.getBytes("utf-8"));
    }

    @Override
    public void init() throws ServletException {

        super.init();
    }

    public static AuthenticateToken getAccessToken(HttpServletRequest request) {
        String accessToken = BaseFilter.getCookie(request, "accessToken");
        if(accessToken==null){
            accessToken=BaseFilter.getHeaderParm(request,"AccessToken");
        }
        try {
            return     parseAccessToken(accessToken);
        }catch (Exception e){
            return null;
        }
    }


    //解析access_token字符串
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static AuthenticateToken parseAccessToken(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            throw new AuthException(AuthCode.OAUTH_FAIL_ILLEGAL_ACCESSTOKEN, "accessToken为空");
        }
        AuthenticateToken token;
        try {
            token = OBJECT_MAPPER.readValue(DatatypeConverter.parseBase64Binary(accessToken), AuthenticateToken.class);
        } catch (Exception e) {
            logger.error("accessToken非法, accessToken:[" + accessToken + "]");
            throw new AuthException(AuthCode.OAUTH_FAIL_ILLEGAL_ACCESSTOKEN, "accessToken非法");
        }
        return token;
    }
}
