package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

/**
 * app推送通知内容
 * 字节数限制：title+content在UTF-8下不超过2000字节，约666个汉字
 */
public class Notification {
    /**
     * 通知标题, 可以为空
     * 安卓支持，IOS暂不支持(IOS通知只发送content，忽略title)
     */
    private String title;

    /**
     * 通知内容，不可以为空
     */
    private String content;

    public Notification(String title, String content) {
        this.title = title;
        this.content = content;
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
}
