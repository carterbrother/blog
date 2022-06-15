package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserThirdpartyAccount;
import com.springbootpractice.demo.easycode.plugin.dao.UserThirdpartyAccountDao;
import com.springbootpractice.demo.easycode.plugin.service.UserThirdpartyAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第三方账号(UserThirdpartyAccount)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userThirdpartyAccountService")
public class UserThirdpartyAccountServiceImpl implements UserThirdpartyAccountService {
    @Resource
    private UserThirdpartyAccountDao userThirdpartyAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserThirdpartyAccount queryById(Integer id) {
        return this.userThirdpartyAccountDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserThirdpartyAccount> queryAllByLimit(int offset, int limit) {
        return this.userThirdpartyAccountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userThirdpartyAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserThirdpartyAccount insert(UserThirdpartyAccount userThirdpartyAccount) {
        this.userThirdpartyAccountDao.insert(userThirdpartyAccount);
        return userThirdpartyAccount;
    }

    /**
     * 修改数据
     *
     * @param userThirdpartyAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserThirdpartyAccount update(UserThirdpartyAccount userThirdpartyAccount) {
        this.userThirdpartyAccountDao.update(userThirdpartyAccount);
        return this.queryById(userThirdpartyAccount.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userThirdpartyAccountDao.deleteById(id) > 0;
    }
}