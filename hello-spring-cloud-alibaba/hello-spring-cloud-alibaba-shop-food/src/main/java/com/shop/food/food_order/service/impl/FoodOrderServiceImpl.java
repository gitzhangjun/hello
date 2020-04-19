package com.shop.food.food_order.service.impl;

import com.shop.food.food_order.entity.FoodOrder;
import com.shop.food.food_order.dao.FoodOrderDao;
import com.shop.food.food_order.service.FoodOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CourseOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Service("foodOrderService")
public class FoodOrderServiceImpl implements FoodOrderService {
    @Resource
    private FoodOrderDao foodOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public FoodOrder queryById(Long orderId) {
        return this.foodOrderDao.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FoodOrder> queryAllByLimit(int offset, int limit) {
        return this.foodOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param foodOrder 实例对象
     * @return 实例对象
     */
    @Override
    public FoodOrder insert(FoodOrder foodOrder) {
        this.foodOrderDao.insert(foodOrder);
        return foodOrder;
    }

    /**
     * 修改数据
     *
     * @param foodOrder 实例对象
     * @return 实例对象
     */
    @Override
    public FoodOrder update(FoodOrder foodOrder) {
        this.foodOrderDao.update(foodOrder);
        return this.queryById(foodOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.foodOrderDao.deleteById(orderId) > 0;
    }
}