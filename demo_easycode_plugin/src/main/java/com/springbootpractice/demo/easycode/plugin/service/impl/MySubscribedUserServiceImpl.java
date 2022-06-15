package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.MySubscribedUser;
import com.springbootpractice.demo.easycode.plugin.dao.MySubscribedUserDao;
import com.springbootpractice.demo.easycode.plugin.service.MySubscribedUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 我关注的用户(MySubscribedUser)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("mySubscribedUserService")
public class MySubscribedUserServiceImpl implements MySubscribedUserService {
    @Resource
    private MySubscribedUserDao mySubscribedUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MySubscribedUser queryById(Integer id) {
        return this.mySubscribedUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MySubscribedUser> queryAllByLimit(int offset, int limit) {
        return this.mySubscribedUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param mySubscribedUser 实例对象
     * @return 实例对象
     */
    @Override
    public MySubscribedUser insert(MySubscribedUser mySubscribedUser) {
        this.mySubscribedUserDao.insert(mySubscribedUser);
        return mySubscribedUser;
    }

    /**
     * 修改数据
     *
     * @param mySubscribedUser 实例对象
     * @return 实例对象
     */
    @Override
    public MySubscribedUser update(MySubscribedUser mySubscribedUser) {
        this.mySubscribedUserDao.update(mySubscribedUser);
        return this.queryById(mySubscribedUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.mySubscribedUserDao.deleteById(id) > 0;
    }
}