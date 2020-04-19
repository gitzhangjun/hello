package com.shop.food.cart.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (Cart)实体类
 *
 * @author makejava
 * @since 2020-03-22 18:10:43
 */
@Data
@Getter
@Setter
public class Cart implements Serializable {
    private static final long serialVersionUID = -13405973748918882L;
    /**
    * 流水ID
    */
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 课程ID
    */
    private Integer foodId;
    /**
    * 数量
    */
    private Integer quantity;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据最后变更时间
    */
    private Date updateTime;


}