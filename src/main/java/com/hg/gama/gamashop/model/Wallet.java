/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: Wallet
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
@SQLDelete(sql = "update wallet set deleted = 1 where id = ?")
public class Wallet {
    private Integer id;
    private String name;
    private Integer balance;
    private Integer freeze;
    private String status;
    private String creator;
    private Date created;
    private Date updated;
    private Integer deleted;

    @Id
    @Basic
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
    @Column(name = "balance", nullable = false)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "freeze", nullable = false)
    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
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
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) &&
                Objects.equals(name, wallet.name) &&
                Objects.equals(balance, wallet.balance) &&
                Objects.equals(freeze, wallet.freeze) &&
                Objects.equals(status, wallet.status) &&
                Objects.equals(creator, wallet.creator) &&
                Objects.equals(created, wallet.created) &&
                Objects.equals(updated, wallet.updated) &&
                Objects.equals(deleted, wallet.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, freeze, status, creator, created, updated, deleted);
    }
}
