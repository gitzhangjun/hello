package com.shop.course.cart.service.impl;

import com.shop.course.cart.dao.CartDao;
import com.shop.course.cart.entity.Cart;
import com.shop.course.cart.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Cart)表服务实现类
 *
 * @author makejava
 * @since 2020-03-22 18:10:43
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Cart queryById(Long id) {
        return this.cartDao.queryById(id);
    }

    /**
     * 通过用户ID、商品ID查询记录
     *
     * @param userId 主键
     * @param courseId
     * @return 实例对象
     */
    @Override
    public List<Cart> queryByUserIdAndCourseId(Long userId, Long courseId) {
        return this.cartDao.queryByUserIdAndCourseId(userId , courseId);
    }

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 对象列表
     */
//    @Override
//    public List<Cart> queryByUserId(Integer userId) {
//        return null;
//    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Cart> queryAllByLimit(int offset, int limit) {
        return this.cartDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public Cart insert(Cart cart) {
        this.cartDao.insert(cart);
        return cart;
    }

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public Cart update(Cart cart) {
        this.cartDao.update(cart);
        return this.cartDao.queryById(cart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cartDao.deleteById(id) > 0;
    }
}