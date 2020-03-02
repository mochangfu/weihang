package com.hg.gama.gamautil.numasutil.messagebus.message.vo;

import com.hg.gama.gamautil.numasutil.messagebus.message.domain.AppMessage;

import java.io.Serializable;

public class AppMessagePushVO implements Serializable {
    private static final long serialVersionUID = 740584579835885800L;
    private String uuid;
    private AppMessage appMessage;

    public AppMessagePushVO(String uuid, AppMessage appMessage) {
        this.uuid = uuid;
        this.appMessage = appMessage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public AppMessage getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(AppMessage appMessage) {
        this.appMessage = appMessage;
    }
}
