package com.shop.course.course.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (Course)实体类
 *
 * @author makejava
 * @since 2020-03-15 23:10:10
 */
@Data
@Setter
@Getter
public class Course implements Serializable {
    private static final long serialVersionUID = -41718909923590728L;
    /**
    * 自增ID
    */
    private Long id;
    /**
    * 课程名
    */
    private String courseName;
    /**
     * 商品状态
     * 0 上架、1 下架 、2 删除
     */
    private Integer status;

    /**
    * 课程简介
    */
    private String introduce;
    /**
    * 零售价，保留2位小数
    */
    private Double retailPrice;
    /**
    * 发货地址
    */
    private String shipAddress;
    /**
    * 图片
    */
    private String picture;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据最后变更时间
    */
    private Date updateTime;

}