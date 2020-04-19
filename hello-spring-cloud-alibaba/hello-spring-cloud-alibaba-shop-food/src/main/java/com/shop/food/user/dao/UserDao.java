package com.shop.food.user.dao;

import com.shop.food.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-15 23:08:13
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);


    /**
     * 根据登录用户名查询用户信息
     * @param account
     * @return
     */
    User queryByAccount(String account);

    /**
     * 登录
     * @param account
     * @param passwd
     * @return
     */
    User login(String account , String passwd);

    /**
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
    List<User> queryAllByLimit(@Param("account") String account ,@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}