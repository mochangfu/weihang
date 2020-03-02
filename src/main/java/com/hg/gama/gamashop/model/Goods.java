/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: RequestUtil
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
@SQLDelete(sql = "update goods set deleted = 1 where id = ?")
public class Goods {
    private Integer id;
    private String name;
    private Integer type;
    private Integer country;
    private Integer address;
    private String summary;
    private String description;
    private String video;
    private String image;
    private Date date;
    private Integer price;
    private Integer currentprice;
    private Integer memberprice;
    private Integer superprice;
    private Integer membergo;
    private Integer supergo;
    private Integer saled;
    private Integer residue;
    private String specifa;
    private Integer creator;
    private Integer testor;
    private Integer deleted;
    private Integer shopId;

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
    @Column(name = "type", nullable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "country", nullable = false)
    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    @Basic
    @Column(name = "address", nullable = false)
    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    @Basic
    @Column(name = "summary", nullable = false, length = 255)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "video", nullable = false, length = 255)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    @Column(name = "currentprice", nullable = false)
    public Integer getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(Integer currentprice) {
        this.currentprice = currentprice;
    }

    @Basic
    @Column(name = "memberprice", nullable = false)
    public Integer getMemberprice() {
        return memberprice;
    }

    public void setMemberprice(Integer memberprice) {
        this.memberprice = memberprice;
    }

    @Basic
    @Column(name = "superprice", nullable = false)
    public Integer getSuperprice() {
        return superprice;
    }

    public void setSuperprice(Integer superprice) {
        this.superprice = superprice;
    }

    @Basic
    @Column(name = "membergo", nullable = false)
    public Integer getMembergo() {
        return membergo;
    }

    public void setMembergo(Integer membergo) {
        this.membergo = membergo;
    }

    @Basic
    @Column(name = "supergo", nullable = false)
    public Integer getSupergo() {
        return supergo;
    }

    public void setSupergo(Integer supergo) {
        this.supergo = supergo;
    }

    @Basic
    @Column(name = "saled", nullable = false)
    public Integer getSaled() {
        return saled;
    }

    public void setSaled(Integer saled) {
        this.saled = saled;
    }

    @Basic
    @Column(name = "residue", nullable = false)
    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
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
    @Column(name = "creator", nullable = false)
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "testor", nullable = false)
    public Integer getTestor() {
        return testor;
    }

    public void setTestor(Integer testor) {
        this.testor = testor;
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
    @Column(name = "shop_id", nullable = false)
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(country, goods.country) &&
                Objects.equals(address, goods.address) &&
                Objects.equals(summary, goods.summary) &&
                Objects.equals(description, goods.description) &&
                Objects.equals(video, goods.video) &&
                Objects.equals(image, goods.image) &&
                Objects.equals(date, goods.date) &&
                Objects.equals(price, goods.price) &&
                Objects.equals(currentprice, goods.currentprice) &&
                Objects.equals(memberprice, goods.memberprice) &&
                Objects.equals(superprice, goods.superprice) &&
                Objects.equals(membergo, goods.membergo) &&
                Objects.equals(supergo, goods.supergo) &&
                Objects.equals(saled, goods.saled) &&
                Objects.equals(residue, goods.residue) &&
                Objects.equals(specifa, goods.specifa) &&
                Objects.equals(creator, goods.creator) &&
                Objects.equals(testor, goods.testor) &&
                Objects.equals(deleted, goods.deleted) &&
                Objects.equals(shopId, goods.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, shopId, country, address, summary, description, video, image, date, price, currentprice, memberprice, superprice, membergo, supergo, saled, residue, specifa, creator, testor, deleted);
    }
}
