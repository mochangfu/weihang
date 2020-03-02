package com.hg.gama.gamautil.numasutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 *
 * @author stellar
 * @date 2016-2-29
 */
@Component
@Lazy(false)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    private static final Pattern REPLACE_PATTERN = Pattern.compile("\\$\\{.+?\\}");

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> Map<String, T> getBeans(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBeansOfType(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        if (SpringContextHolder.applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext; // NOSONAR
    }


    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is null");
        }
    }

    /**
     * 获取当前环境启动profile
     */
    public Collection<String> getActiveProfiles() {
        return Arrays.asList(getApplicationContext().getEnvironment().getActiveProfiles());
    }


    public static String getProperty(String name) {
        String property = null;
        if (getApplicationContext() != null) {
            property = getApplicationContext().getEnvironment().getProperty(name);
        }
        return property;
    }

    public static String getProperty(String name, String defaultVal) {
        String val = getProperty(name);
        return val == null ? defaultVal : val;
    }

    public static Integer getInt(String key) {
        String v = getProperty(key);
        if (v == null) {
            return null;
        }
        return Integer.parseInt(v);
    }

    public static int getInt(String key, int defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Integer.parseInt(getProperty(key));
    }

    public static Long getLong(String key) {
        if (getProperty(key) == null) {
            return null;
        }
        return Long.parseLong(getProperty(key));
    }

    public static long getLong(String key, long defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Long.parseLong(getProperty(key));
    }

    public static Boolean getBoolean(String key) {
        if (getProperty(key) == null) {
            return false;
        }
        return Boolean.parseBoolean(getProperty(key));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(getProperty(key));
    }

    public static Float getFloat(String key) {
        if (getProperty(key) == null) {
            return null;
        }
        return Float.parseFloat(getProperty(key));
    }

    public static float getFloat(String key, float defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Float.parseFloat(getProperty(key));
    }

    public static Double getDouble(String key) {
        if (getProperty(key) == null) {
            return null;
        }
        return Double.parseDouble(getProperty(key));
    }

    public static double getDouble(String key, double defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Double.parseDouble(getProperty(key));
    }

    public static String replaceWithEnvVars(String input) {
        Set<String> matches = new HashSet<String>();
        Matcher m = REPLACE_PATTERN.matcher(input);
        while (m.find()) {
            matches.add(m.group());
        }
        for (String s : matches) {
            String env = System.getenv(s.substring(2, s.length() - 1));
            if (!StringUtils.isEmpty(env)) {
                input = input.replace(s, env);
            } else {
                input = input.replace(s, "");
            }
        }

        return input;
    }

}