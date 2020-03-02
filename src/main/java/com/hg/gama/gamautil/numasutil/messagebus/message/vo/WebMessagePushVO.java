package com.hg.gama.gamautil.numasutil.messagebus.message.vo;


import com.hg.gama.gamautil.numasutil.messagebus.message.domain.WebMessage;

import java.io.Serializable;

public class WebMessagePushVO implements Serializable {
    private static final long serialVersionUID = 2793570006810045551L;
    private String uuid;
    private WebMessage content;

    public WebMessagePushVO(String uuid, WebMessage content) {
        this.uuid = uuid;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public WebMessage getContent() {
        return content;
    }

    public void setContent(WebMessage content) {
        this.content = content;
    }
}
