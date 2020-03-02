package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.io.Serializable;

public class AppMessage implements Serializable {

    private static final long serialVersionUID = 5430258883770900199L;
    /**
     * 消息内容，不能为空
     */
    private String content;

    /**
     * 消息标题，可以为空
     */
    private String title;

    /**
     * app跳转地址标识，可以为空
     */
    private String appTargetUri;

    /**
     * app跳转额外参数，可以为空
     */
    private String appParams;

    public AppMessage(String content) {
        this.content = content;
    }

    public AppMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public AppMessage(String title, String content, String appTargetUri, String appParams) {
        this.title = title;
        this.content = content;
        this.appTargetUri = appTargetUri;
        this.appParams = appParams;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppTargetUri() {
        return appTargetUri;
    }

    public void setAppTargetUri(String appTargetUri) {
        this.appTargetUri = appTargetUri;
    }

    public String getAppParams() {
        return appParams;
    }

    public void setAppParams(String appParams) {
        this.appParams = appParams;
    }

    @Override
    public String toString() {
        return "AppMessage{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", appTargetUri='" + appTargetUri + '\'' +
                ", appParams='" + appParams + '\'' +
                '}';
    }
}
