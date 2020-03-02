/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: Cart
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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
@Where(clause = " deleted = 0 ")
@SQLDelete(sql = "update cart set deleted = 1 where id = ?")

public class Cart {
    private Integer id;
    private String name;
    private Integer goods;
    private Integer count;
    private String specifa;
    private String creator;
    private Date created;
    private Date updated;
    private Integer deleted;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "goods", nullable = false)
    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "specifa", nullable = false, length = 255)
    public String getSpecifa() {
        return specifa;
    }

    public void setSpecifa(String specifa) {
        this.specifa = specifa;
    }

    @Basic
    @Column(name = "creator", nullable = false, length = 255)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
    @Column(name = "updated", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(name, cart.name) &&
                Objects.equals(goods, cart.goods) &&
                Objects.equals(count, cart.count) &&
                Objects.equals(specifa, cart.specifa) &&
                Objects.equals(creator, cart.creator) &&
                Objects.equals(created, cart.created) &&
                Objects.equals(updated, cart.updated) &&
                Objects.equals(deleted, cart.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goods, count, specifa, creator, created, updated, deleted);
    }
}
