package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 8651275754519370342L;

    /**
     * 消息内容，不能为空
     */
    private String content;

    /**
     * 消息标题，可以为空
     */
    private String title;
    /**
     * web跳转地址标识，可以为空
     */
    private String webTargetUri;

    /**
     * web跳转额外参数，可以为空
     */
    private String webParams;

    /**
     * app跳转地址标识，可以为空
     */
    private String appTargetUri;

    /**
     * app跳转额外参数，可以为空
     */
    private String appParams;

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public Message(String content, String title, String webTargetUri, String webParams, String appTargetUri, String appParams) {
        this.content = content;
        this.title = title;
        this.webTargetUri = webTargetUri;
        this.webParams = webParams;
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

    public WebMessage buildWebMessage() {
        return new WebMessage(title, content, webTargetUri, webParams);
    }

    public AppMessage buildAppMessage() {
        return new AppMessage(title, content, appTargetUri, appParams);
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", webTargetUri='" + webTargetUri + '\'' +
                ", webParams='" + webParams + '\'' +
                ", appTargetUri='" + appTargetUri + '\'' +
                ", appParams='" + appParams + '\'' +
                '}';
    }
}
