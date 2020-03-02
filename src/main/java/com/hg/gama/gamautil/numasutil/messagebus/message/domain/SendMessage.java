package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.io.Serializable;
import java.util.Set;

public class SendMessage implements Serializable {
    private Set<Integer> userIds;
    private WebMessage message;
    private String recordUuid;

    public SendMessage(Set<Integer> userIds, WebMessage message) {
        this.userIds = userIds;
        this.message = message;
    }

    public SendMessage(Set<Integer> userIds, WebMessage message, String recordUuid) {
        this.userIds = userIds;
        this.message = message;
        this.recordUuid = recordUuid;
    }

    public Set<Integer> getUserIds() {
        return userIds;
    }

    public WebMessage getMessage() {
        return message;
    }

    public String getRecordUuid() {
        return recordUuid;
    }
}
