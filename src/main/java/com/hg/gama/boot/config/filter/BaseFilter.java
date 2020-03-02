/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.hg.gama.boot.config.filter;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ant语法
 * 请参考 http://jinnianshilongnian.iteye.com/blog/1416322
 * /dd;/ee,/ff /list
 * 过滤器介绍：
 *
 * @author Zhang Kaitao
 */
public abstract class BaseFilter implements Filter {

    private FilterConfig config = null;

    private final String[] nullStingArray = new String[0];
    private final String urlSplitPattern = "[, ;\r\n]";//逗号  空格 分号  换行

    private final PatternMatcher pathMatcher = new ServletPathMatcher();

    private final Logger logger = LoggerFactory.getLogger("url.filter");

    /**
     * 白名单
     */
    private String[] whiteListURLs = null;

    /**
     * 黑名单
     */
    private String[] blackListURLs = null;

    @Override
    public final void init(FilterConfig config) throws ServletException {
        this.config = config;
        initConfig();
        init();
    }

    /**
     * 子类覆盖
     *
     * @throws ServletException
     */
    public void init() throws ServletException {

    }

    private String getCurrentUrl(HttpServletRequest httpRequest) {
        String extraPath = httpRequest.getPathInfo();
        String servletPath = httpRequest.getServletPath();
        if (extraPath != null) {
            return servletPath + extraPath;
        }
        return servletPath;
    }

    /**
     * 1、黑名单匹配
     * 2、白名单匹配
     */
    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String currentURL = getCurrentUrl(httpRequest);

        logger.debug("url filter : current url : [{}]", currentURL);

        if (isBlackURL(currentURL)) {
            chain.doFilter(request, response);
            return;
        }

        if (!isWhiteURL(currentURL)) {
            chain.doFilter(request, response);
            return;
        }
        doFilter(httpRequest, httpResponse, chain);
        return;
    }

    private boolean isWhiteURL(String currentURL) {
        for (String whiteURL : whiteListURLs) {
            if (pathMatcher.matches(whiteURL, currentURL)) {
                logger.debug("url filter : white url list matches : [{}] match [{}] continue", currentURL, whiteURL);
                return true;
            }
        }
        logger.debug("url filter : white url list not matches : [{}] not match [{}]",
                currentURL, Arrays.toString(whiteListURLs));
        return false;
    }

    private boolean isBlackURL(String currentURL) {
        for (String blackURL : blackListURLs) {
            if (pathMatcher.matches(blackURL, currentURL)) {
                logger.debug("url filter : black url list matches : [{}] match [{}] break", currentURL, blackURL);
                return true;
            }
        }
        logger.debug("url filter : black url list not matches : [{}] not match [{}]",
                currentURL, Arrays.toString(blackListURLs));
        return false;
    }

    /**
     * 子类覆盖
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    /**
     * 子类覆盖
     */
    @Override
    public void destroy() {

    }

    private void initConfig() {
        String whiteListURLStr = config.getInitParameter("whiteListURL");
        whiteListURLs = strToArray(whiteListURLStr);


        String blackListURLStr = config.getInitParameter("blackListURL");
        blackListURLs = strToArray(blackListURLStr);

    }

    private String[] strToArray(String urlStr) {
        if (urlStr == null) {
            return nullStingArray;
        }
        String[] urlArray = urlStr.split(urlSplitPattern);

        List<String> urlList = new ArrayList<>();

        for (String url : urlArray) {
            url = url.trim();
            if (url.isEmpty()) {
                continue;
            }

            urlList.add(url);
        }

        return urlList.toArray(nullStingArray);
    }

    public FilterConfig getConfig() {
        return config;
    }

    protected static String getCookie(final HttpServletRequest request, final String cookieDomainName) {
        if (StringUtils.isBlank(cookieDomainName)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookieDomainName.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }
        return null;
    }
    protected static String getHeaderParm(final HttpServletRequest request, final String parmName) {
        String e0= request.getHeader("AccessToken");
        try {
            return e0.toString();
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


}


