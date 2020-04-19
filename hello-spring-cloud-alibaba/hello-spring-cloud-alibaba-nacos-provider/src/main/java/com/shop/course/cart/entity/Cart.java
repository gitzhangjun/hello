package com.shop.course.cart.entity;

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
@Setter
@Getter
public class Cart implements Serializable {
    private static final long serialVersionUID = -13405973748918882L;
    /**
    * 流水ID
    */
    private Long id;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 课程ID
    */
    private Long courseId;
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