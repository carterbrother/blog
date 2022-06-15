package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.Carrier;
import com.springbootpractice.demo.easycode.plugin.dao.CarrierDao;
import com.springbootpractice.demo.easycode.plugin.service.CarrierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运营商(Carrier)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("carrierService")
public class CarrierServiceImpl implements CarrierService {
    @Resource
    private CarrierDao carrierDao;

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    @Override
    public Carrier queryById(String code) {
        return this.carrierDao.queryById(code);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Carrier> queryAllByLimit(int offset, int limit) {
        return this.carrierDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param carrier 实例对象
     * @return 实例对象
     */
    @Override
    public Carrier insert(Carrier carrier) {
        this.carrierDao.insert(carrier);
        return carrier;
    }

    /**
     * 修改数据
     *
     * @param carrier 实例对象
     * @return 实例对象
     */
    @Override
    public Carrier update(Carrier carrier) {
        this.carrierDao.update(carrier);
        return this.queryById(carrier.getCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String code) {
        return this.carrierDao.deleteById(code) > 0;
    }
}