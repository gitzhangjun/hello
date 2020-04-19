package com.shop.food.admin.service;

import com.shop.food.admin.entity.Admin;
import java.util.List;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2020-03-15 13:00:57
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Admin queryById(Long id);

    /**
     * 用户登录
     * @param account
     * @param passwd
     * @return
     */
    Admin login(String account , String passwd);

    /**
     * 根据登录用户名去查询用户信息
     * @param account
     * @return
     */
    Admin queryByAccount(String account);

    /**
     * 数据总条数
     *
     * @param account 账号信息
     * @return 总条数
     */
    int queryCount(String account);

    /**
     * 查询多条数据
     * @param account 账号信息
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(String account , int offset , int limit);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改密码
     *
     * @param admin 实例对象
     * @return 实例对象
     */
//    Admin changePasswd(Admin admin);


    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}