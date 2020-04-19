package com.shop.food.user.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-15 23:08:09
 */
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -82697233471869936L;
    /**
    * 自增ID
    */
    private Integer userId;
    /**
    * 用户登录账号
    */
    private String account;
    
    private String passwd;
    /**
    * 用户页面显示名字
    */
    private String userName;
    /**
    * 用户真实名字
    */
    private String trueName;
    /**
    * 用户手机号码
    */
    private String mobile;
    /**
    * 用户邮箱
    */
    private String email;
    /**
    * 用户邮编
    */
    private String zipCode;
    /**
    * 用户邮编
    */
    private String deliveryAddre;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据最后变更时间
    */
    private Date updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDeliveryAddre() {
        return deliveryAddre;
    }

    public void setDeliveryAddre(String deliveryAddre) {
        this.deliveryAddre = deliveryAddre;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String masking() {

        if (Objects.isNull(this)){
            return null;
        }

        this.setPasswd("");
        this.setZipCode("");
        this.setDeliveryAddre("");
        this.setEmail("");
        this.setMobile("");
        this.setTrueName("");
        this.setCreateTime(null);
        this.setUpdateTime(null);
        return JSON.toJSONString(this);
    }
}