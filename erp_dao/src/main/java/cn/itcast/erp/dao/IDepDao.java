package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Dep;

import java.util.List;

/**
 * 部门数据访问接口
 */
public interface IDepDao {
    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList();
}
