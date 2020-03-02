package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.util.Date;

public class NotificationRecordDO {
    private Integer id;
    /**
     * 医院id
     */
    private String hospitalId;
    /**
     * 通知标题，安卓支持，IOS不支持
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 接收时间
     */
    private Date receiveTime;
    /**
     * 唯一标识
     */
    private String uuid;

    public NotificationRecordDO(String hospitalId, String title, String content, Date receiveTime, String uuid) {
        this.hospitalId = hospitalId;
        this.title = title;
        this.content = content;
        this.receiveTime = receiveTime;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
