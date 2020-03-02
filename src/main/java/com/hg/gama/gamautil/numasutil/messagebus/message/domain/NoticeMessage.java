package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.io.Serializable;

public class NoticeMessage implements Serializable {

    private long sn;

    private int userId;

    private String type;

    private Object payload;

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
