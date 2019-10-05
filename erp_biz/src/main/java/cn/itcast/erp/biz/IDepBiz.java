package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Dep;

import java.util.List;

public interface IDepBiz {
    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList();
}
