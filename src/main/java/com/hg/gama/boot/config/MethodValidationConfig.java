package com.hg.gama.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 方法级别注解校验处理器
 */
@Configuration
public class MethodValidationConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setOrder(OrderConfig.VALIDATION_ORDER);
        return new MethodValidationPostProcessor();
    }
}
