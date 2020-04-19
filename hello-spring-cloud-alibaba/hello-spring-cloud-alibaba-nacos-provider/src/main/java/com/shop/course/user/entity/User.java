package com.shop.course.user.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = -82697233471869936L;
    /**
    * 自增ID
    */
    private Long userId;
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