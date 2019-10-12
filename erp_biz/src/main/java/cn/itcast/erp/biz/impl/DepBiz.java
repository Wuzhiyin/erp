package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

import java.util.List;

/**
 * 业务逻辑类
 */
public class DepBiz extends BaseBiz<Dep> implements IDepBiz{
    private IDepDao depDao;

    public void setDepDao(IDepDao depDao) {
        this.depDao = depDao;
        super.setBaseDao(this.depDao);
    }
}
