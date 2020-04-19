package com.shop.food.admin.dao;

import com.shop.food.admin.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-15 13:00:57
 */
@Mapper
public interface AdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Admin queryById(Long id);

    /**
     * 根据登录用户名查询用户信息
     * @param account
     * @return
     */
   Admin queryByAccount(String account);

    Admin login(String account , String passwd);
    /**
     *
     * 数据总条数
     *
     * @param account 账号信息
     * @return 实例对象
     */
    int queryCount(String account);

    /**
     * 查询指定行数据
     * @param account 账号信息
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(@Param("account") String account , @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> queryAll(Admin admin);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}