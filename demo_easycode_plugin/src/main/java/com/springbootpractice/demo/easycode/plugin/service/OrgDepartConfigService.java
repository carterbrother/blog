package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDepartConfig;
import java.util.List;

/**
 * 公司的部门配置(OrgDepartConfig)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public interface OrgDepartConfigService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrgDepartConfig queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrgDepartConfig> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orgDepartConfig 实例对象
     * @return 实例对象
     */
    OrgDepartConfig insert(OrgDepartConfig orgDepartConfig);

    /**
     * 修改数据
     *
     * @param orgDepartConfig 实例对象
     * @return 实例对象
     */
    OrgDepartConfig update(OrgDepartConfig orgDepartConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}