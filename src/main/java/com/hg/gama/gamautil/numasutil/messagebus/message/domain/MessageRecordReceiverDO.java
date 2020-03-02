package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.util.Date;

public class MessageRecordReceiverDO {

    private Integer id;
    private String hospitalId;
    private Integer recordId;
    private Integer receiverId;
    private String uuid;
    private Date webReadTime;

    public MessageRecordReceiverDO() {
    }

    public MessageRecordReceiverDO(String hospitalId, Integer receiverId, String uuid) {
        this.hospitalId = hospitalId;
        this.receiverId = receiverId;
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

    public Date getWebReadTime() {
        return webReadTime;
    }

    public void setWebReadTime(Date webReadTime) {
        this.webReadTime = webReadTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
