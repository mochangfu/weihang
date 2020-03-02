package com.hg.gama.boot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.hg.gama.boot.config.filter.CorsFilter;
import com.hg.gama.boot.config.filter.IpFilter;
import com.hg.gama.boot.config.filter.LoginFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Value("${gama.login.filter.whitelist}")
    private String whiteList;

    @Value("${gama.login.filter.blacklist}")
    private String blackList;

    @Value("${gama.login.filter.ext.blacklist:}")
    private String blackListExt;

    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Bean("corsFilter")
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean("ipFilter")
    public FilterRegistrationBean ipFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new IpFilter());
        registration.addUrlPatterns("/*");
        registration.setName("ipFilter");
        registration.setOrder(2);
        return registration;
    }


    @Bean("loginFilter")
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        String blackListTemp = blackList;
        if (StringUtils.isNotEmpty(blackListExt)) {
            blackListTemp += ", " + blackListExt;
        }
        registration.addInitParameter("blackListURL", blackListTemp);
        registration.addInitParameter("whiteListURL", whiteList);
        registration.setName("loginFilter");
        registration.setOrder(3);
        registration.setAsyncSupported(true);
        return registration;
    }

    @Bean("druidWebStatFilter")
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WebStatFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("exclusions",
                "/app-update/*, /tablet/*, /templates/*, /web/*, /normal/*, /druid/*, *.js, *.gif, *.jpg, *.png, *.css, *.ico");
        registration.setName("druidWebStatFilter");
        registration.setOrder(4);
        registration.setAsyncSupported(true);
        return registration;
    }

    @Bean("druidStatView")
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

 /*   @Bean("docResourceServlet")
    public ServletRegistrationBean docResourceServlet() {
        return new ServletRegistrationBean(new DocResourceServlet(), "/web/input/*");
    }
*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //server_sys.properties文件中allow.cors配置项决定是否开启跨域
        boolean allowCors = Boolean.valueOf(environment.getProperty("allow.cors"));
        if (allowCors) {
            registry.addMapping("/**")    //匹配了所有的URL
                    .allowedHeaders("*")  //允许跨域请求包含任意的头信息
                    .allowedMethods("*")  //允许外域发起请求任意HTTP Method
                    .allowedOrigins("*"); //允许所有的外域发起跨域请求
        }
    }

}