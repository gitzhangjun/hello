package com.shop.course.admin.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2020-03-15 13:00:57
 */

@Data
@Getter
@Setter
public class Admin implements Serializable {
    private static final long serialVersionUID = -16034258263476015L;
    /**
    * 自增ID
    */
    private Long id;
    /**
    * 管理员登录账号
    */
    private String account;
    /**
    * 管理员登录密码
    */
    private String passwd;
    /**
    * 管理员手机号码
    */
    private String mobile;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据最后变更时间
    */
    private Date updateTime;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", passwd='" + passwd + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String masking() {

        if (Objects.isNull(this)){
            return null;
        }

        this.setPasswd("");
        this.setMobile("");
        this.setCreateTime(null);
        this.setUpdateTime(null);
        return JSON.toJSONString(this);
    }
}