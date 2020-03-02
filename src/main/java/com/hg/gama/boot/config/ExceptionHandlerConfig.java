package com.hg.gama.boot.config;


import com.hg.gama.gamautil.numasutil.exception.DefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerConfig implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    @Autowired
    private DefaultExceptionHandler exceptionHandler;


    @Override
    public void afterPropertiesSet() {
        if (exceptionHandler != null) {
            exceptionHandler.addExceptionTraceFilterCode(String.valueOf(CodeConstants.PLT_SECURITY_LOGIN_ACCOUNT_ERROR));
        }
    }
}
