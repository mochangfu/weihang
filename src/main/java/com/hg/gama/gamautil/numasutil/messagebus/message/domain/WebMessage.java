package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

import java.io.Serializable;

public class WebMessage implements Serializable {

    private static final long serialVersionUID = 5156178024613055771L;
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

    public WebMessage(String content) {
        this.content = content;
    }

    public WebMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public WebMessage(String title, String content, String webTargetUri, String webParams) {
        this.title = title;
        this.content = content;
        this.webTargetUri = webTargetUri;
        this.webParams = webParams;
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

    @Override
    public String toString() {
        return "WebMessage{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", webTargetUri='" + webTargetUri + '\'' +
                ", webParams='" + webParams + '\'' +
                '}';
    }
}
