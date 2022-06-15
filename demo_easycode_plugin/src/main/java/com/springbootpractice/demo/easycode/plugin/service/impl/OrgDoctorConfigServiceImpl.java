package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDoctorConfig;
import com.springbootpractice.demo.easycode.plugin.dao.OrgDoctorConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.OrgDoctorConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机构跟医生的关系(OrgDoctorConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("orgDoctorConfigService")
public class OrgDoctorConfigServiceImpl implements OrgDoctorConfigService {
    @Resource
    private OrgDoctorConfigDao orgDoctorConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrgDoctorConfig queryById(Long id) {
        return this.orgDoctorConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrgDoctorConfig> queryAllByLimit(int offset, int limit) {
        return this.orgDoctorConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orgDoctorConfig 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDoctorConfig insert(OrgDoctorConfig orgDoctorConfig) {
        this.orgDoctorConfigDao.insert(orgDoctorConfig);
        return orgDoctorConfig;
    }

    /**
     * 修改数据
     *
     * @param orgDoctorConfig 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDoctorConfig update(OrgDoctorConfig orgDoctorConfig) {
        this.orgDoctorConfigDao.update(orgDoctorConfig);
        return this.queryById(orgDoctorConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orgDoctorConfigDao.deleteById(id) > 0;
    }
}