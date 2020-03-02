package com.hg.gama.gamautil.numasutil.messagebus;

public interface MessageBus {
    void register(IMessageListener listener);

    void send(Message message);
}
