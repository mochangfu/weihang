/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: SpecialOption
 * Author: 25414
 * Date: 2019/5/2 20:57
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
@Table(name = "special_option", schema = "gama", catalog = "")
@SQLDelete(sql = "update special_option set deleted = 1 where id = ?")
public class SpecialOption {
    private Integer id;
    private Integer specialId;
    private Integer no;
    private String name;
    @JsonIgnore
    private String creator;
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
    @Column(name = "special_id", nullable = false)
    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
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
        SpecialOption that = (SpecialOption) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialId, that.specialId) &&
                Objects.equals(no, that.no) &&
                Objects.equals(name, that.name) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialId, no, name, creator, created, updated, deleted);
    }
}
