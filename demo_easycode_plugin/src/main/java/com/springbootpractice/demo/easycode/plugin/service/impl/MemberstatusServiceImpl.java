package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.Memberstatus;
import com.springbootpractice.demo.easycode.plugin.dao.MemberstatusDao;
import com.springbootpractice.demo.easycode.plugin.service.MemberstatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Memberstatus)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("memberstatusService")
public class MemberstatusServiceImpl implements MemberstatusService {
    @Resource
    private MemberstatusDao memberstatusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Memberstatus queryById(Integer id) {
        return this.memberstatusDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Memberstatus> queryAllByLimit(int offset, int limit) {
        return this.memberstatusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param memberstatus 实例对象
     * @return 实例对象
     */
    @Override
    public Memberstatus insert(Memberstatus memberstatus) {
        this.memberstatusDao.insert(memberstatus);
        return memberstatus;
    }

    /**
     * 修改数据
     *
     * @param memberstatus 实例对象
     * @return 实例对象
     */
    @Override
    public Memberstatus update(Memberstatus memberstatus) {
        this.memberstatusDao.update(memberstatus);
        return this.queryById(memberstatus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.memberstatusDao.deleteById(id) > 0;
    }
}