package com.shop.course.cart.service;

import com.shop.course.cart.entity.Cart;

import java.util.List;

/**
 * (Cart)表服务接口
 *
 * @author makejava
 * @since 2020-03-22 18:10:43
 */
public interface CartService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cart queryById(Long id);

    /**
     * 通过用户ID、商品ID查询记录
     *
     * @param userId 主键
     * @return 实例对象
     */
    List<Cart> queryByUserIdAndCourseId(Long userId , Long shopId);

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 对象列表
     */
    //List<Cart> queryByUserId(Long userId);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Cart> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    Cart insert(Cart cart);

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    Cart update(Cart cart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}