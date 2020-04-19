package com.shop.course.course_order.service.impl;

import com.shop.course.course_order.service.CourseOrderService;
import com.shop.course.course_order.entity.CourseOrder;
import com.shop.course.course_order.dao.CourseOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CourseOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Service("courseOrderService")
public class CourseOrderServiceImpl implements CourseOrderService {
    @Resource
    private CourseOrderDao courseOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public CourseOrder queryById(Long orderId) {
        return this.courseOrderDao.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CourseOrder> queryAllByLimit(int offset, int limit) {
        return this.courseOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param courseOrder 实例对象
     * @return 实例对象
     */
    @Override
    public CourseOrder insert(CourseOrder courseOrder) {
        this.courseOrderDao.insert(courseOrder);
        return courseOrder;
    }

    /**
     * 修改数据
     *
     * @param courseOrder 实例对象
     * @return 实例对象
     */
    @Override
    public CourseOrder update(CourseOrder courseOrder) {
        this.courseOrderDao.update(courseOrder);
        return this.courseOrderDao.queryById(courseOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.courseOrderDao.deleteById(orderId) > 0;
    }
}