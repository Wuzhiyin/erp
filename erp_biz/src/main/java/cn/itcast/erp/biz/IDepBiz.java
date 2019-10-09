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
    List<Dep> getList(Dep dep1,Dep dep2,Object param,int firstResult,int maxResults);

    long getCount(Dep dep1,Dep dep2,Object param);
    /**
     * 新增
     * @param dep
     */
    public void add(Dep dep);

    /**
     * 删除
     */
    void delete(Long uuid);
    /**
     * 通过编号查询对象
     * @param uuid
     * @return
     */
    Dep get(Long uuid);


    /**
     * 更新
     */
    void update(Dep dep);
}
