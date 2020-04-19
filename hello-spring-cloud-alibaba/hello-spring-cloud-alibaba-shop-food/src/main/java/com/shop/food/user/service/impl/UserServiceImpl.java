package com.shop.food.user.service.impl;

import com.shop.food.user.entity.User;
import com.shop.food.user.dao.UserDao;
import com.shop.food.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 23:08:19
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.userDao.queryById(userId);
    }

    @Override
    public User queryByAccount(String account) {
        return this.userDao.queryByAccount(account);
    }

    @Override
    public User login(String account, String passwd) {
        return this.userDao.login(account,passwd);
    }

    /**
     * 数据总条数
     *
     * @param account 课件名
     * @return 总条数
     */
    public int queryCount(String account){
        return userDao.queryCount(account);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(String accountName , int offset, int limit) {
        return this.userDao.queryAllByLimit(accountName , offset , limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        user.setPasswd("");
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userDao.deleteById(userId) > 0;
    }
}