package com.hg.gama.gamautil.numasutil.messagebus;

import java.io.Serializable;

public interface IMessageListener<T extends Serializable> {

    String getToken();

    String getMessageType();

    void consumeMessage(T message);
}
