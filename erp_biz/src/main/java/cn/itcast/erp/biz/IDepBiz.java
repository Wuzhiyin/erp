package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Dep;

import java.util.List;

public interface IDepBiz {
    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList();

    /**
     * 条件查询
     * @param dep1
     * @return
     */
    List<Dep> getList(Dep dep1,int firstResult,int maxResults);

    long getCount(Dep dep1);
}
