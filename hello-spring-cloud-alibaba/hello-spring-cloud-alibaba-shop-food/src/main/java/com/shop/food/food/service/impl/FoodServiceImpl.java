package com.shop.food.food.service.impl;

import com.shop.food.food.dao.FoodDao;
import com.shop.food.food.entity.Food;
import com.shop.food.food.service.FoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Course)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 23:10:10
 */
@Service("courseService")
public class FoodServiceImpl implements FoodService {
    @Resource
    private FoodDao foodDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Food queryById(Integer id) {
        return this.foodDao.queryById(id);
    }

    /**
     * 数据总条数
     *
     * @param courseName 课件名
     * @return 总条数
     */
    public int queryCount(String courseName){
        return this.queryCount(courseName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Food> queryAllByLimit(String courseName , int offset, int limit) {
        return this.foodDao.queryAllByLimit(courseName , offset, limit);
    }

    /**
     * 新增数据
     *
     * @param food 实例对象
     * @return 实例对象
     */
    @Override
    public Food insert(Food food) {
        this.foodDao.insert(food);
        return food;
    }

    /**
     * 修改数据
     *
     * @param food 实例对象
     * @return 实例对象
     */
    @Override
    public Food update(Food food) {
        this.foodDao.update(food);
        return this.queryById(food.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.foodDao.deleteById(id) > 0;
    }
}