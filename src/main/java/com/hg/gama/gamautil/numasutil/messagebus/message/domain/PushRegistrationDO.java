package com.hg.gama.gamautil.numasutil.messagebus.message.domain;

public class PushRegistrationDO {
    private Integer id;
    /**
     * 业务标识,目前只有护理管理的app
     */
    private String biz;
    /**
     * 医院id
     */
    private String hospitalId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 手机imei
     */
    private String imei;
    /**
     * 注册ID，这里为极光推送对设备的标识ID，可以基于设备推送;
     */
    private String registerId;

    /**
     * 推送ID, 这里为极光推送的别名;把别名设置为userId, 可以基于用户推送
     */
    private String pushId;
    /**
     * 标签，可以设置为hospitalId，可以基于医院推送
     */
    private String tag;
    /**
     * 1开启0关闭
     */
    private Integer status;

    public PushRegistrationDO() {
    }

    public PushRegistrationDO(String biz, String hospitalId, Integer userId, String imei, String registerId, String pushId, String tag,
            Integer status) {
        this.biz = biz;
        this.hospitalId = hospitalId;
        this.userId = userId;
        this.imei = imei;
        this.registerId = registerId;
        this.pushId = pushId;
        this.tag = tag;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
