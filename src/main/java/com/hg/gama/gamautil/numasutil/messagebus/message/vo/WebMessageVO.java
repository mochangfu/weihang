package com.hg.gama.gamautil.numasutil.messagebus.message.vo;

import java.io.Serializable;
import java.util.Date;

public class WebMessageVO implements Serializable {
    private static final long serialVersionUID = 8746970943515189187L;
    private String hospitalId;
    private String module;
    private String title;
    private String content;
    private String webTargetUri;
    private String webParams;
    private Date receiveTime;
    private String uuid;

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

    public String getWebParams() {
        return webParams;
    }

    public void setWebParams(String webParams) {
        this.webParams = webParams;
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
