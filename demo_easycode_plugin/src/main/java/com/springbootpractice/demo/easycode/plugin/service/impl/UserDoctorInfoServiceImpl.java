package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserDoctorInfo;
import com.springbootpractice.demo.easycode.plugin.dao.UserDoctorInfoDao;
import com.springbootpractice.demo.easycode.plugin.service.UserDoctorInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生信息(UserDoctorInfo)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userDoctorInfoService")
public class UserDoctorInfoServiceImpl implements UserDoctorInfoService {
    @Resource
    private UserDoctorInfoDao userDoctorInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserDoctorInfo queryById(Long id) {
        return this.userDoctorInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserDoctorInfo> queryAllByLimit(int offset, int limit) {
        return this.userDoctorInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userDoctorInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserDoctorInfo insert(UserDoctorInfo userDoctorInfo) {
        this.userDoctorInfoDao.insert(userDoctorInfo);
        return userDoctorInfo;
    }

    /**
     * 修改数据
     *
     * @param userDoctorInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserDoctorInfo update(UserDoctorInfo userDoctorInfo) {
        this.userDoctorInfoDao.update(userDoctorInfo);
        return this.queryById(userDoctorInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDoctorInfoDao.deleteById(id) > 0;
    }
}