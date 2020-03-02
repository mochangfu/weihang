package com.hg.gama.gamautil.numasutil.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CookieUtil {
    public static String getCookie(final HttpServletRequest request, final String cookieDomainName) {
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
}
