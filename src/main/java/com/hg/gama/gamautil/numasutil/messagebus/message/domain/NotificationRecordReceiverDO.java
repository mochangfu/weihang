package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.util.Date;

public class NotificationRecordReceiverDO {
    private Integer id;
    private String hospitalId;
    private Integer recordId;
    private Integer receiverId;
    private String uuid;
    private Date readTime;

    public NotificationRecordReceiverDO(Integer recordId, Integer receiverId, String hospitalId, String uuid) {
        this.recordId = recordId;
        this.receiverId = receiverId;
        this.hospitalId = hospitalId;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}
