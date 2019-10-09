package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 部门数据访问类
 */
public class DepDao extends HibernateDaoSupport implements IDepDao {
    /**
     * 查询全部列表
     * @return
     */
    public List<Dep> getList() {
        return (List<Dep>) getHibernateTemplate().find("from cn.itcast.erp.entity.Dep");
    }

    /**
     * 条件查询
     * @param dep1
     * @return
     */
    public List<Dep> getList(Dep dep1,Dep dep2,Object param,int firstResult,int maxResults) {
        DetachedCriteria dc = getDetachedCriteria(dep1);
        return (List<Dep>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
    }
    /**
     * 记录条件查询的总记录数
     * @param dep1
     * @return
     */
    public long getCount(Dep dep1,Dep dep2,Object param) {
        DetachedCriteria dc = getDetachedCriteria(dep1);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }
    /**
     * 新增
     * @param dep
     */
    public void add(Dep dep){
        this.getHibernateTemplate().save(dep);
    }
    /**
     * 删除
     */
    public void delete(Long uuid) {
        //让对象进入持久化状态
        Dep dep = this.getHibernateTemplate().get(Dep.class, uuid);
        //删除持久化状态
        this.getHibernateTemplate().delete(dep);
    }

    /**
     * 通过编号查询对象
     * @param uuid
     * @return
     */
    public Dep get(Long uuid){
        return getHibernateTemplate().get(Dep.class, uuid);
    }
    /**
     * 更新
     */
    public void update(Dep dep){
        this.getHibernateTemplate().update(dep);
    }
    private DetachedCriteria getDetachedCriteria(Dep dep1){
        DetachedCriteria dc =DetachedCriteria.forClass(Dep.class);
        if(null != dep1){
            //是否输入部门名称
            if(null != dep1.getName() && dep1.getName().trim().length() > 0){
                //MatchMode.ANYWHERE => % %
                //MatchMode.END =>      %name
                //MatchMode.START  =>   name%
                dc.add(Restrictions.like("name", dep1.getName(), MatchMode.ANYWHERE));
            }
            //是否输入部门的电话
            if(null != dep1.getTele() && dep1.getTele().trim().length() > 0){
                dc.add(Restrictions.like("tele", dep1.getTele(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }

}
