package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.MyManagedUser;
import com.springbootpractice.demo.easycode.plugin.dao.MyManagedUserDao;
import com.springbootpractice.demo.easycode.plugin.service.MyManagedUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 我管理的用户(MyManagedUser)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("myManagedUserService")
public class MyManagedUserServiceImpl implements MyManagedUserService {
    @Resource
    private MyManagedUserDao myManagedUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyManagedUser queryById(Integer id) {
        return this.myManagedUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MyManagedUser> queryAllByLimit(int offset, int limit) {
        return this.myManagedUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param myManagedUser 实例对象
     * @return 实例对象
     */
    @Override
    public MyManagedUser insert(MyManagedUser myManagedUser) {
        this.myManagedUserDao.insert(myManagedUser);
        return myManagedUser;
    }

    /**
     * 修改数据
     *
     * @param myManagedUser 实例对象
     * @return 实例对象
     */
    @Override
    public MyManagedUser update(MyManagedUser myManagedUser) {
        this.myManagedUserDao.update(myManagedUser);
        return this.queryById(myManagedUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.myManagedUserDao.deleteById(id) > 0;
    }
}