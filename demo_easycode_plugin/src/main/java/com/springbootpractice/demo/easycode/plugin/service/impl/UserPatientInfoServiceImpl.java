package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserPatientInfo;
import com.springbootpractice.demo.easycode.plugin.dao.UserPatientInfoDao;
import com.springbootpractice.demo.easycode.plugin.service.UserPatientInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 患者信息(UserPatientInfo)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userPatientInfoService")
public class UserPatientInfoServiceImpl implements UserPatientInfoService {
    @Resource
    private UserPatientInfoDao userPatientInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserPatientInfo queryById(Long id) {
        return this.userPatientInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserPatientInfo> queryAllByLimit(int offset, int limit) {
        return this.userPatientInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userPatientInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserPatientInfo insert(UserPatientInfo userPatientInfo) {
        this.userPatientInfoDao.insert(userPatientInfo);
        return userPatientInfo;
    }

    /**
     * 修改数据
     *
     * @param userPatientInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserPatientInfo update(UserPatientInfo userPatientInfo) {
        this.userPatientInfoDao.update(userPatientInfo);
        return this.queryById(userPatientInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userPatientInfoDao.deleteById(id) > 0;
    }
}