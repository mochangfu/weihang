package com.hg.gama.gamautil.numasutil.messagebus;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hg.gama.gamautil.numasutil.util.UUIDGen;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class Message<T extends Serializable> implements Serializable {
    private Set<String> tokens;
    private T body;
    private String messageType;
    private String messageId = UUIDGen.UUID();
    private Map<String, String> extraInfo = Maps.newHashMap();

    public Message() {

    }

    public Message(T body) {
        this(body, (String[]) null);
    }

    public Message(T body, String... tks) {
        this.body = body;
        this.tokens = Sets.newHashSet();
        if (body != null) {
            messageType = body.getClass().getName();
        }
        if (tks != null) {
            Collections.addAll(this.tokens, tks);
        }
    }

    public static <T extends Serializable> Message<T> create(T body) {
        return new Message<>(body);
    }

    public Set<String> getTokens() {
        return tokens;
    }

    public void setTokens(Set<String> token) {
        this.tokens = token;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getMessageType() {
        return messageType;
    }

    public Message<T> setMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }
}
