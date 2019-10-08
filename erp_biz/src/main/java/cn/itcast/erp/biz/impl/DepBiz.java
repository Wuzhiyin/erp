package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

import java.util.List;

/**
 * 业务逻辑类
 */
public class DepBiz implements IDepBiz {
    private IDepDao depDao;

    public void setDepDao(IDepDao depDao) {
        this.depDao = depDao;
    }

    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList() {
        return depDao.getList();
    }

    /**
     * 条件查询
     * @param dep1
     * @return
     */
    public List<Dep> getList(Dep dep1,int firstResult,int maxResults) {
        return depDao.getList(dep1,firstResult,maxResults);
    }

    public long getCount(Dep dep1) {
        return depDao.getCount(dep1);
    }
}
