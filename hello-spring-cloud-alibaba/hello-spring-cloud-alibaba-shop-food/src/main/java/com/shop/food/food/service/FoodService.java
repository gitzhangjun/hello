package com.shop.food.food.service;

import com.shop.food.food.entity.Food;

import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author makejava
 * @since 2020-03-15 23:10:10
 */
public interface FoodService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Food queryById(Integer id);

    int queryCount(String courseName);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Food> queryAllByLimit(String courseName , int offset, int limit);

    /**
     * 新增数据
     *
     * @param food 实例对象
     * @return 实例对象
     */
    Food insert(Food food);

    /**
     * 修改数据
     *
     * @param food 实例对象
     * @return 实例对象
     */
    Food update(Food food);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}