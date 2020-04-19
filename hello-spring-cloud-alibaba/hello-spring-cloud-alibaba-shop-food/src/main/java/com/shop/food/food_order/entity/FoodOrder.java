package com.shop.food.food_order.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (CourseOrder)实体类
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Data
@Getter
@Setter
public class FoodOrder implements Serializable {
    private static final long serialVersionUID = -21699313907074019L;
    /**
    * 订单ID
    */
    private Long orderId;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 菜品ID
    */
    private Integer foodId;
    /**
    * 数量
    */
    private Integer quantity;
    /**
    * 价格
    */
    private Double price;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据最后变更时间
    */
    private Date updateTime;

}