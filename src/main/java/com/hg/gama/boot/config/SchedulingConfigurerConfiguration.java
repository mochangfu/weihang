package com.hg.gama.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulingConfigurerConfiguration implements SchedulingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfigurerConfiguration.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setErrorHandler(t -> LOGGER.error("", t));
        taskScheduler.initialize();
        taskScheduler.setPoolSize(2);
        taskRegistrar.setTaskScheduler(taskScheduler);
    }

}