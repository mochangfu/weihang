/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderChange
 * Author: 25414
 * Date: 2019/5/3 17:52
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "order_change", schema = "gama", catalog = "")
public class OrderChange {
    private Integer id;
    private Integer order;
    private String msg;
    private Integer deleted;
    private Date created;
    private Integer creator;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order", nullable = false)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Basic
    @Column(name = "msg", nullable = false, length = 255)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
    @Column(name = "created", nullable = true)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "creator", nullable = true)
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
        OrderChange that = (OrderChange) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(created, that.created) &&
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, msg, deleted, created, creator);
    }
}
