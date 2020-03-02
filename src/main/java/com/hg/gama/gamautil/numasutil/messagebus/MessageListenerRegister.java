package com.hg.gama.gamautil.numasutil.messagebus;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class MessageListenerRegister implements ApplicationContextAware, InitializingBean {

    @Autowired
    private MessageBus messageBus;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, MessageListener> listeners = applicationContext.getBeansOfType(MessageListener.class);
        for (Map.Entry<String, MessageListener> listenerEntry : listeners.entrySet()) {
            messageBus.register(listenerEntry.getValue());
        }
    }
}
