package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserLoginPublicwechat;
import com.springbootpractice.demo.easycode.plugin.dao.UserLoginPublicwechatDao;
import com.springbootpractice.demo.easycode.plugin.service.UserLoginPublicwechatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小程序进入的公众号记录(UserLoginPublicwechat)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userLoginPublicwechatService")
public class UserLoginPublicwechatServiceImpl implements UserLoginPublicwechatService {
    @Resource
    private UserLoginPublicwechatDao userLoginPublicwechatDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserLoginPublicwechat queryById(Long id) {
        return this.userLoginPublicwechatDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserLoginPublicwechat> queryAllByLimit(int offset, int limit) {
        return this.userLoginPublicwechatDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userLoginPublicwechat 实例对象
     * @return 实例对象
     */
    @Override
    public UserLoginPublicwechat insert(UserLoginPublicwechat userLoginPublicwechat) {
        this.userLoginPublicwechatDao.insert(userLoginPublicwechat);
        return userLoginPublicwechat;
    }

    /**
     * 修改数据
     *
     * @param userLoginPublicwechat 实例对象
     * @return 实例对象
     */
    @Override
    public UserLoginPublicwechat update(UserLoginPublicwechat userLoginPublicwechat) {
        this.userLoginPublicwechatDao.update(userLoginPublicwechat);
        return this.queryById(userLoginPublicwechat.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userLoginPublicwechatDao.deleteById(id) > 0;
    }
}