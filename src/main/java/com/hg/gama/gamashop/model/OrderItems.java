/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderItems
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
@Where(clause = " deleted = 0 ")
@SQLDelete(sql = "update order_items set deleted = 1 where id = ?")
@Table(name = "order_items", schema = "gama", catalog = "")
public class OrderItems {

    private Integer id;
    private Integer orderId;
    private String name;
    private Integer shopId;
    private Integer goodsId;
    private Integer goodsCaseId;
    private Integer count;
    private Date created;
    private Integer deleted=0;
    private Date updated;
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
    @Column(name = "order_id", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
    @Column(name = "shop_id", nullable = false)
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "goods_id", nullable = false)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_case_Id", nullable = false)
    public Integer getGoodsCaseId() {
        return goodsCaseId;
    }

    public void setGoodsCaseId(Integer goodsCaseId) {
        this.goodsCaseId = goodsCaseId;
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
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
    @Column(name = "updated", nullable = true)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
        OrderItems that = (OrderItems) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(shopId, that.shopId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(goodsCaseId, that.goodsCaseId) &&
                Objects.equals(count, that.count) &&
                Objects.equals(created, that.created) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, name, shopId, goodsId, goodsCaseId, count, creator, created, deleted, updated);
    }
}
