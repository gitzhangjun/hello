package com.shop.food.user.service;

import com.shop.food.user.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-03-15 23:08:15
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * 通过用户账号查询用户单条数据
     *
     * @param account 主键
     * @return 实例对象
     */
    User queryByAccount(String account);

    /**
     * 用户登录
     *
     * @param account 用户名
     * @return 实例对象
     */
    User login(String account  ,String passwd);

    /**
     * 数据总条数
     *
     * @param account 账号信息
     * @return 总条数
     */
    int queryCount(String account);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(String account , int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

}