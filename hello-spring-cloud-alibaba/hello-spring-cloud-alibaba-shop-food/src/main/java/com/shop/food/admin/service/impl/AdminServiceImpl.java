package com.shop.food.admin.service.impl;

import com.shop.food.admin.service.AdminService;
import com.shop.food.admin.entity.Admin;
import com.shop.food.admin.dao.AdminDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 13:00:57
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Long id) {
        Admin admin = adminDao.queryById(id);
//        admin.setPasswd("");
        return admin;
    }



    /**
     * 登录
     *
     * @param account 主键
     * @return 实例对象
     */
    @Override
    public Admin login(String account , String passwd) {
        Admin admin = adminDao.login(account,passwd);
        return admin;
    }

    /**
     * 根据登录用户名去查询用户信息
     *
     * @param account
     * @return
     */
    @Override
    public Admin queryByAccount(String account) {
        return this.adminDao.queryByAccount(account);
    }

    /**
     * 数据总条数
     *
     * @param account 账号信息
     * @return 实例对象
     */
    public int queryCount(String account) {
        return adminDao.queryCount(account);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAllByLimit(String account , int offset, int limit) {
        return this.adminDao.queryAllByLimit(account,offset, limit);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        admin.setPasswd("");
        return admin;
    }


    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.adminDao.deleteById(id) > 0;
    }
}