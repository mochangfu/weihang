/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: User
 * Author: 25414
 * Date: 2019/5/2 0:04
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
@SQLDelete(sql = "update user set deleted = 1 where id = ?")
public class User {
    private Integer id;
    private String username;
    private String realname;
    private String nickname;
    private Date birthday;
    private Integer sex;
    private String avatar;
    private String email;
    private String telephone;
    private String qq;
    private String weichat;
    @JsonIgnore
    private String passwd;
    private Integer creator;
    private Integer deleted;
    private Integer approved;
    private Integer member;
    private Integer area;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "realname", nullable = false, length = 255)
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 255)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 255)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "weichat", nullable = true, length = 255)
    public String getWeichat() {
        return weichat;
    }

    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 255)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "creator", nullable = true)
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
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
    @Column(name = "approved", nullable = true)
    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    @Basic
    @Column(name = "member", nullable = true)
    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    @Basic
    @Column(name = "area", nullable = true)
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(realname, user.realname) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(qq, user.qq) &&
                Objects.equals(weichat, user.weichat) &&
                Objects.equals(passwd, user.passwd) &&
                Objects.equals(creator, user.creator) &&
                Objects.equals(deleted, user.deleted) &&
                Objects.equals(approved, user.approved) &&
                Objects.equals(member, user.member) &&
                Objects.equals(area, user.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, realname, nickname, birthday, sex, avatar, email, telephone, qq, weichat, passwd, creator, deleted, approved, member, area);
    }
}
