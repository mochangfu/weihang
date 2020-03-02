package com.hg.gama.gamautil.numasutil.messagebus;

public abstract class MessageBusAware {
    public void beforeSend(Message message) {
    }

    public void afterSend(Message message) {
    }

    public void beforeReceive(Message message) {
    }

    public void afterReceive(Message message) {
    }
}
