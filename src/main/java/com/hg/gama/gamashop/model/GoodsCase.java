/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: GoodsCase
 * Author: 25414
 * Date: 2019/5/2 20:36
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Where(clause = " deleted = 0 ")
@Table(name = "goods_case", schema = "gama", catalog = "")
@SQLDelete(sql = "update goods_case set deleted = 1 where id = ?")
public class GoodsCase {
    private Integer id;
    private Integer goodsId;
    private String specialOptionIdList;
    private Integer price;
    @JsonIgnore
    private String creator;
    @JsonIgnore
    private Date created;
    @JsonIgnore
    private Date updated;
    @JsonIgnore
    private Integer deleted;
    private Integer stock;
    private String unit;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "special_option_id_list", nullable = false, length = 255)
    public String getSpecialOptionIdList() {
        return specialOptionIdList;
    }

    public void setSpecialOptionIdList(String specialOptionIdList) {
        this.specialOptionIdList = specialOptionIdList;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
        GoodsCase that = (GoodsCase) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(specialOptionIdList, that.specialOptionIdList) &&
                Objects.equals(price, that.price) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, specialOptionIdList, price, creator, created, updated, deleted);
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "unit", nullable = false, length = 255)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
