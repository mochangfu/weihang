/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderVo
 * Author: 25414
 * Date: 2019/5/1 18:49
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "order_info", schema = "gama", catalog = "")
@Where(clause = " deleted = 0 ")
@SQLDelete(sql = "update order_info set deleted = 1 where id = ?")
public class OrderInfo {

    private Integer id;
    private String sn;
    private Integer userId;
    private String orderName;
    private Integer addressId;
    private String status;
    private Date created;
    private Date updated;
    private Integer deleted=0;
    private Integer creator;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sn", nullable = false, length = 255)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "order_name", nullable = false, length = 255)
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Basic
    @Column(name = "address_Id", nullable = false)
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 64)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "updated", nullable = true)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "deleted", nullable = false)
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


    @Basic
    @Column(name = "creator", nullable = false)
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(id, orderInfo.id) &&
                Objects.equals(sn, orderInfo.sn) &&
                Objects.equals(userId, orderInfo.userId) &&
                Objects.equals(orderName, orderInfo.orderName) &&
                Objects.equals(addressId, orderInfo.addressId) &&
                Objects.equals(status, orderInfo.status) &&
                Objects.equals(created, orderInfo.created) &&
                Objects.equals(updated, orderInfo.updated) &&
                Objects.equals(deleted, orderInfo.deleted) &&
                Objects.equals(creator, orderInfo.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sn, userId, orderName, addressId, status, created, updated, creator, deleted);
    }
}
