/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: Special
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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
@Where(clause = " deleted = 0 ")
@SQLDelete(sql = "update special set deleted = 1 where id = ?")
public class Special {
    private Integer id;
    private Integer goodsId;
    private Integer no;
    private String name;
    @JsonIgnore
    private Integer creator;
    @JsonIgnore
    private Date created;
    @JsonIgnore
    private Date updated;
    @JsonIgnore
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
    @Column(name = "goods_id", nullable = false)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "no", nullable = false)
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer sortNo) {
        this.no = sortNo;
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
    @Column(name = "creator", nullable = false)
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
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
        Special special = (Special) o;
        return Objects.equals(id, special.id) &&
                Objects.equals(goodsId, special.goodsId) &&
                Objects.equals(no, special.no) &&
                Objects.equals(name, special.name) &&
                Objects.equals(creator, special.creator) &&
                Objects.equals(created, special.created) &&
                Objects.equals(updated, special.updated) &&
                Objects.equals(deleted, special.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, no, name, creator, created, updated, deleted);
    }
}
