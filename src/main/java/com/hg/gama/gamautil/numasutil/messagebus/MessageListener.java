package com.hg.gama.gamautil.numasutil.messagebus;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageListener<T extends Serializable> implements IMessageListener<T> {

    @Autowired
    private MessageBus messageBus;

    @Override
    public abstract String getToken();

    @Override
    public String getMessageType() {
        Class type = getClass();
        Type parameterizedType = null;
        while (!type.equals(MessageListener.class)) {
            parameterizedType = type.getGenericSuperclass();
            type = type.getSuperclass();
        }
        Object obj = ((ParameterizedType) parameterizedType).getActualTypeArguments()[0];
        return ((Class<T>) obj).getTypeName();
    }

    @Override
    public abstract void consumeMessage(T message);

    @PostConstruct
    public void init() throws Exception {
        messageBus.register(this);
    }
}
