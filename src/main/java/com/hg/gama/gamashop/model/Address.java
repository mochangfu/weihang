/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: Address
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
@SQLDelete(sql = "update address set deleted = 1 where id = ?")
public class Address {
    private Integer id;
    private String name;
    private String address;
    private String addressDetail;
    private Integer isdefault;
    private String privince;
    private String city;
    private String area;
    private String creator;
    private String mobile;
    private Integer code;
    private Date created;
    private Date updated;
    private Integer deleted =0;

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
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "address_detail", nullable = false, length = 255)
    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Basic
    @Column(name = "isdefault", nullable = false)
    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    @Basic
    @Column(name = "privince", nullable = false, length = 11)
    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 11)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "area", nullable = false, length = 11)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
    @Column(name = "mobile", nullable = false, length = 255)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "code", nullable = false)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) &&
                Objects.equals(name, address1.name) &&
                Objects.equals(address, address1.address) &&
                Objects.equals(addressDetail, address1.addressDetail) &&
                Objects.equals(isdefault, address1.isdefault) &&
                Objects.equals(privince, address1.privince) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(area, address1.area) &&
                Objects.equals(creator, address1.creator) &&
                Objects.equals(mobile, address1.mobile) &&
                Objects.equals(code, address1.code) &&
                Objects.equals(created, address1.created) &&
                Objects.equals(updated, address1.updated) &&
                Objects.equals(deleted, address1.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, addressDetail, isdefault, privince, city, area, creator, mobile, code, created, updated, deleted);
    }
}
