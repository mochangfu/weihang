package com.hg.gama.boot.config.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
public class CtxServletListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //用于jsp获取/numas
        event.getServletContext().setAttribute("ctx", event.getServletContext().getContextPath());
    }


}
