package com.hg.gama.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
//上传配置类

@Configuration
public class WebFileConfig extends WebMvcConfigurerAdapter {
//public class WebAppConfig extends WebMvcConfigurationSupport {
private static final Logger logger = LoggerFactory.getLogger(WebFileConfig.class);
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${gama.file.path}")
    private String mImagesPath;
 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+mImagesPath+"/images/");
        super.addResourceHandlers(registry);
    }
 
}