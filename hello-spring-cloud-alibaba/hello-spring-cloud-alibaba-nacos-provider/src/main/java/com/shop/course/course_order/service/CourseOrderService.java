package com.shop.course.course_order.service;

import com.shop.course.course_order.entity.CourseOrder;
import java.util.List;

/**
 * (CourseOrder)表服务接口
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
public interface CourseOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    CourseOrder queryById(Long orderId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CourseOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param courseOrder 实例对象
     * @return 实例对象
     */
    CourseOrder insert(CourseOrder courseOrder);

    /**
     * 修改数据
     *
     * @param courseOrder 实例对象
     * @return 实例对象
     */
    CourseOrder update(CourseOrder courseOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderId);

}