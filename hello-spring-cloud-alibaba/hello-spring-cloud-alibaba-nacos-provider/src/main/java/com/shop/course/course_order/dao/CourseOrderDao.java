package com.shop.course.course_order.dao;

import com.shop.course.course_order.entity.CourseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (CourseOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Mapper
public interface CourseOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    CourseOrder queryById(Long orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CourseOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param courseOrder 实例对象
     * @return 对象列表
     */
    List<CourseOrder> queryAll(CourseOrder courseOrder);

    /**
     * 新增数据
     *
     * @param courseOrder 实例对象
     * @return 影响行数
     */
    int insert(CourseOrder courseOrder);

    /**
     * 修改数据
     *
     * @param courseOrder 实例对象
     * @return 影响行数
     */
    int update(CourseOrder courseOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(Long orderId);

}