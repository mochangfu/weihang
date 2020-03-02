package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import com.hg.gama.gamautil.numasutil.messagebus.message.constant.MessagePushType;

import java.util.Date;

public class MessageRecordDO {
    private Integer id;
    /**
     * 医院id，不能为空
     */
    private String hospitalId;
    /**
     * 模块标识，不能为空
     */
    private String module;
    /**
     * 消息标题，可以为空
     */
    private String title;
    /**
     * 消息内容，不能为空
     */
    private String content;

    /**
     * web跳转地址标识，可以为空
     */
    private String webTargetUri;

    /**
     * app跳转地址标识，可以为空
     */
    private String appTargetUri;

    /**
     * web跳转额外参数，可以为空
     */
    private String webParams;

    /**
     * app跳转额外参数，可以为空
     */
    private String appParams;

    /**
     * 消息接收时间
     */
    private Date receiveTime;

    /**
     * uuid
     */
    private String uuid;

    private MessagePushType type;

    public MessageRecordDO() {
    }

    public MessageRecordDO(String hospitalId, String module, String title, String content, String webTargetUri, String appTargetUri,
            String webParams, String appParams, Date receiveTime, String recordUuid, MessagePushType type) {
        this.hospitalId = hospitalId;
        this.module = module;
        this.title = title;
        this.content = content;
        this.webTargetUri = webTargetUri;
        this.appTargetUri = appTargetUri;
        this.webParams = webParams;
        this.appParams = appParams;
        this.receiveTime = receiveTime;
        uuid = recordUuid;
        this.type = type;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public String getWebTargetUri() {
        return webTargetUri;
    }

    public void setWebTargetUri(String webTargetUri) {
        this.webTargetUri = webTargetUri;
    }

    public String getAppTargetUri() {
        return appTargetUri;
    }

    public void setAppTargetUri(String appTargetUri) {
        this.appTargetUri = appTargetUri;
    }

    public String getWebParams() {
        return webParams;
    }

    public void setWebParams(String webParams) {
        this.webParams = webParams;
    }

    public String getAppParams() {
        return appParams;
    }

    public void setAppParams(String appParams) {
        this.appParams = appParams;
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

    public MessagePushType getType() {
        return type;
    }

    public void setType(MessagePushType type) {
        this.type = type;
    }

}
